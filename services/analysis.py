from datetime import datetime

from services.storage import calculate_storage_risk
from services.nir import generate_demo_spectrum

from ml.predict import predict_nutrition


def safe_float(value, default=0):
    try:
        return float(value)
    except (TypeError, ValueError):
        return default


def analyze_sample(data, image_path=None):

    sample_name = data.get(
        "sample_name",
        "Unnamed Sample"
    )

    sample_type = data.get(
        "sample_type",
        "Feed"
    )

    moisture = safe_float(
        data.get("moisture"),
        14.2
    )

    ph = safe_float(
        data.get("ph"),
        5.8
    )

    temperature = safe_float(
        data.get("temperature"),
        28
    )

    humidity = safe_float(
        data.get("humidity"),
        65
    )

    # =========================================================
    # NIR + ML NUTRITION PREDICTION
    # =========================================================

    ml_result = None
    ml_error = None

   try:

    # Current spectrometer implementation is still a
    # simulated/demo spectrum.
    spectrum = generate_demo_spectrum()

    nir_points = spectrum.get(
        "spectrum",
        []
    )
    
    nir_values = [
        point["absorbance"]
        for point in nir_points
    ]
    
    ml_result = predict_nutrition(
        moisture=moisture,
        ph=ph,
        temperature=temperature,
        humidity=humidity,
        nir_values=nir_values
    )

except Exception as error:

    ml_error = str(error)
    # =========================================================
    # NUTRITION VALUES
    # =========================================================

    if ml_result is not None:

        protein = ml_result["protein"]
        fiber = ml_result["fiber"]
        energy = ml_result["energy"]

    else:

        # Keep the system from crashing if the model is
        # unavailable. These are fallback placeholders.
        protein = None
        fiber = None
        energy = None

    # =========================================================
    # STORAGE RISK
    # =========================================================

    storage_risk = calculate_storage_risk(
        moisture,
        temperature,
        humidity
    )

    # =========================================================
    # MOULD / SPOILAGE RISK
    # =========================================================

    mould_score = 0

    if moisture >= 18:
        mould_score += 3
    elif moisture >= 14:
        mould_score += 2
    else:
        mould_score += 1

    if humidity >= 75:
        mould_score += 3
    elif humidity >= 65:
        mould_score += 2
    else:
        mould_score += 1

    if temperature >= 30:
        mould_score += 3
    elif temperature >= 25:
        mould_score += 2
    else:
        mould_score += 1

    if mould_score >= 8:
        mould_risk = "HIGH"

    elif mould_score >= 5:
        mould_risk = "MEDIUM"

    else:
        mould_risk = "LOW"

    # =========================================================
    # ADULTERATION RISK
    # =========================================================

    adulteration_score = 0

    if ph < 4.5 or ph > 7.5:
        adulteration_score += 2

    if moisture > 20:
        adulteration_score += 2

    if adulteration_score >= 3:
        adulteration_risk = "HIGH"

    elif adulteration_score >= 1:
        adulteration_risk = "MEDIUM"

    else:
        adulteration_risk = "LOW"

    # =========================================================
    # QUALITY SCORE
    # =========================================================

    quality_score = 100

    if moisture > 18:
        quality_score -= 20

    elif moisture > 14:
        quality_score -= 10

    if storage_risk == "HIGH":
        quality_score -= 15

    elif storage_risk == "MEDIUM":
        quality_score -= 7

    if mould_risk == "HIGH":
        quality_score -= 15

    elif mould_risk == "MEDIUM":
        quality_score -= 7

    if adulteration_risk == "HIGH":
        quality_score -= 15

    elif adulteration_risk == "MEDIUM":
        quality_score -= 7

    quality_score = max(
        0,
        min(100, quality_score)
    )

    # =========================================================
    # CONFIDENCE
    # =========================================================

    if ml_result is not None:

        # This is a DEMO model confidence indicator,
        # not a scientifically validated confidence score.
        confidence = 70

    else:

        confidence = 0

    # =========================================================
    # FARMER ADVISORY
    # =========================================================

    advisory = []

    if moisture >= 18:
        advisory.append(
            "Monitor moisture closely."
        )

    if humidity >= 75:
        advisory.append(
            "Improve storage ventilation."
        )

    if temperature >= 30:
        advisory.append(
            "Monitor storage temperature."
        )

    if mould_risk in ["MEDIUM", "HIGH"]:
        advisory.append(
            "Inspect the sample for visible spoilage."
        )

    if not advisory:
        advisory.append(
            "Storage conditions are currently within "
            "the prototype safe range."
        )

    # =========================================================
    # ANALYSIS STATUS
    # =========================================================

    if ml_result is not None:

        analysis_status = "ML demo model"

        analysis_note = (
            "Nutrition values were generated by a "
            "Random Forest model trained on synthetic "
            "demo data. They are not laboratory validated."
        )

    else:

        analysis_status = "ML unavailable"

        analysis_note = (
            "ML prediction could not be completed. "
            f"Reason: {ml_error}"
        )

    # =========================================================
    # RETURN RESULT
    # =========================================================

    return {

        "sample_name":
            sample_name,

        "sample_type":
            sample_type,

        "created_at":
            datetime.now().isoformat(),

        "moisture":
            moisture,

        "ph":
            ph,

        "temperature":
            temperature,

        "humidity":
            humidity,

        "quality_score":
            quality_score,

        "protein":
            protein,

        "fiber":
            fiber,

        "energy":
            energy,

        "mould_risk":
            mould_risk,

        "adulteration_risk":
            adulteration_risk,

        "storage_risk":
            storage_risk,

        "confidence":
            confidence,

        "advisory":
            advisory,

        "visual_screening": {

            "status":
                "prototype",

            "message":
                "Prototype visual screening completed"
        },

        "ml_analysis": {

            "status":
                analysis_status,

            "model":
                (
                    ml_result["model"]
                    if ml_result
                    else None
                ),

            "mode":
                (
                    ml_result["mode"]
                    if ml_result
                    else None
                ),

            "validated":
                False
        },

        "analysis_note":
            analysis_note,

        "image_path":
            image_path
    }
