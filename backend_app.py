from flask import Flask, request, jsonify, send_file
from flask_cors import CORS

import os
import uuid

from werkzeug.utils import secure_filename

from database import (
    init_db,
    insert_sample,
    get_all_samples,
    get_sample,
    insert_sensor,
    get_latest_sensor
)

from services.analysis import analyze_sample
from services.nir import generate_demo_spectrum
from services.storage import calculate_storage_risk
from services.pdf_report import create_pdf
from services.qr import create_qr


# =========================================================
# APP SETUP
# =========================================================

app = Flask(__name__)

CORS(app)

BASE_DIR = os.path.dirname(os.path.abspath(__file__))

UPLOAD_FOLDER = os.path.join(BASE_DIR, "uploads")
REPORT_FOLDER = os.path.join(BASE_DIR, "reports")
QR_FOLDER = os.path.join(BASE_DIR, "qr_codes")

os.makedirs(UPLOAD_FOLDER, exist_ok=True)
os.makedirs(REPORT_FOLDER, exist_ok=True)
os.makedirs(QR_FOLDER, exist_ok=True)

app.config["MAX_CONTENT_LENGTH"] = 10 * 1024 * 1024


# =========================================================
# HEALTH CHECK
# =========================================================

@app.route("/api/health", methods=["GET"])
def health():

    return jsonify({
        "success": True,
        "status": "online",
        "service": "FeedSense Backend"
    })


# =========================================================
# ANALYZE SAMPLE
# =========================================================

@app.route("/api/analyze", methods=["POST"])
def analyze():

    try:

        # -------------------------------------------------
        # JSON OR FORM DATA
        # -------------------------------------------------

        if request.is_json:
            data = request.get_json()
            image = None

        else:
            data = request.form.to_dict()
            image = request.files.get("image")

            if image is None:
                image = request.files.get("photo")

        # -------------------------------------------------
        # DEFAULT VALUES
        # -------------------------------------------------

        data["sample_name"] = data.get(
            "sample_name",
            "Unnamed Sample"
        )

        data["sample_type"] = data.get(
            "sample_type",
            "Feed"
        )

        # -------------------------------------------------
        # SAVE IMAGE
        # -------------------------------------------------

        image_path = None

        if image and image.filename:

            safe_name = secure_filename(image.filename)

            unique_name = (
                str(uuid.uuid4())
                + "_"
                + safe_name
            )

            image_path = os.path.join(
                UPLOAD_FOLDER,
                unique_name
            )

            image.save(image_path)

        # -------------------------------------------------
        # RUN ANALYSIS
        # -------------------------------------------------

        result = analyze_sample(
            data,
            image_path
        )

        # -------------------------------------------------
        # SAVE TO DATABASE
        # -------------------------------------------------

        sample_id = insert_sample(result)

        saved_sample = get_sample(sample_id)

        return jsonify({
            "success": True,
            "message": "Sample analyzed successfully",
            "sample_id": sample_id,
            "result": saved_sample
        }), 200

    except Exception as e:

        return jsonify({
            "success": False,
            "error": str(e)
        }), 500


# =========================================================
# GET ALL SAMPLES
# =========================================================

@app.route("/api/samples", methods=["GET"])
def samples():

    try:

        all_samples = get_all_samples()

        return jsonify({
            "success": True,
            "count": len(all_samples),
            "samples": all_samples
        })

    except Exception as e:

        return jsonify({
            "success": False,
            "error": str(e)
        }), 500


# =========================================================
# GET ONE SAMPLE
# =========================================================

@app.route("/api/samples/<int:sample_id>", methods=["GET"])
def sample_details(sample_id):

    sample = get_sample(sample_id)

    if sample is None:

        return jsonify({
            "success": False,
            "message": "Sample not found"
        }), 404

    return jsonify({
        "success": True,
        "sample": sample
    })


# =========================================================
# NIR DEMO
# =========================================================

@app.route("/api/nir-demo", methods=["GET"])
def nir_demo():

    return jsonify(
        generate_demo_spectrum()
    )


# =========================================================
# SENSOR DATA
# =========================================================

@app.route("/api/sensors", methods=["POST"])
def add_sensor():

    try:

        data = request.get_json()

        if not data:

            return jsonify({
                "success": False,
                "message": "JSON data required"
            }), 400

        from datetime import datetime

        sensor_data = {
            "created_at": datetime.now().isoformat(),
            "moisture": float(data.get("moisture", 0)),
            "temperature": float(data.get("temperature", 0)),
            "humidity": float(data.get("humidity", 0)),
            "ph": float(data.get("ph", 0))
        }

        insert_sensor(sensor_data)

        return jsonify({
            "success": True,
            "message": "Sensor data saved",
            "data": sensor_data
        })

    except Exception as e:

        return jsonify({
            "success": False,
            "error": str(e)
        }), 500


# =========================================================
# LATEST SENSOR DATA
# =========================================================

@app.route("/api/sensors/latest", methods=["GET"])
def latest_sensor():

    data = get_latest_sensor()

    if data is None:

        return jsonify({
            "success": False,
            "message": "No sensor data available"
        }), 404

    return jsonify({
        "success": True,
        "data": data
    })


# =========================================================
# STORAGE STATUS
# =========================================================

@app.route("/api/storage-status", methods=["GET"])
def storage_status():

    sensor = get_latest_sensor()

    # Allow manual testing with URL parameters
    if sensor is not None:

        moisture = sensor["moisture"]
        temperature = sensor["temperature"]
        humidity = sensor["humidity"]

    else:

        moisture = float(
            request.args.get("moisture", 14.2)
        )

        temperature = float(
            request.args.get("temperature", 29)
        )

        humidity = float(
            request.args.get("humidity", 72)
        )

    risk = calculate_storage_risk(
        moisture,
        temperature,
        humidity
    )

    return jsonify({
        "success": True,
        "storage": {
            "moisture": moisture,
            "temperature": temperature,
            "humidity": humidity,
            "risk": risk
        }
    })


# =========================================================
# PDF REPORT
# =========================================================

@app.route("/api/report/<int:sample_id>", methods=["GET"])
def report(sample_id):

    sample = get_sample(sample_id)

    if sample is None:

        return jsonify({
            "success": False,
            "message": "Sample not found"
        }), 404

    filename = f"FeedSense_Report_FS-{sample_id}.pdf"

    filepath = os.path.join(
        REPORT_FOLDER,
        filename
    )

    create_pdf(
        sample,
        filepath
    )

    return send_file(
        filepath,
        as_attachment=True,
        download_name=filename,
        mimetype="application/pdf"
    )


# =========================================================
# QR DIGITAL PASSPORT
# =========================================================

@app.route("/api/qr/<int:sample_id>", methods=["GET"])
def qr_code(sample_id):

    sample = get_sample(sample_id)

    if sample is None:

        return jsonify({
            "success": False,
            "message": "Sample not found"
        }), 404

    filename = f"FeedSense_QR_FS-{sample_id}.png"

    filepath = os.path.join(
        QR_FOLDER,
        filename
    )

    create_qr(
        sample,
        filepath
    )

    return send_file(
        filepath,
        mimetype="image/png"
    )


# =========================================================
# START SERVER
# =========================================================

if __name__ == "__main__":

    init_db()

    print("")
    print("===================================")
    print("       FeedSense Backend")
    print("===================================")
    print("Server: http://127.0.0.1:5000")
    print("")
    print("Health:")
    print("http://127.0.0.1:5000/api/health")
    print("===================================")
    print("")

    app.run(
        host="0.0.0.0",
        port=5000,
        debug=True
    )