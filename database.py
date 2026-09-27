import sqlite3
import os
import json

BASE_DIR = os.path.dirname(os.path.abspath(__file__))
DB_PATH = os.path.join(BASE_DIR, "feedsense.db")


def get_connection():
    connection = sqlite3.connect(DB_PATH)
    connection.row_factory = sqlite3.Row
    return connection


def init_db():
    connection = get_connection()
    cursor = connection.cursor()

    cursor.execute("""
        CREATE TABLE IF NOT EXISTS samples (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            sample_name TEXT NOT NULL,
            sample_type TEXT NOT NULL,
            created_at TEXT NOT NULL,

            moisture REAL,
            ph REAL,
            temperature REAL,
            humidity REAL,

            quality_score REAL,
            protein REAL,
            fiber REAL,
            energy REAL,

            mould_risk TEXT,
            adulteration_risk TEXT,
            storage_risk TEXT,

            confidence REAL,
            advisory TEXT,
            image_path TEXT
        )
    """)

    cursor.execute("""
        CREATE TABLE IF NOT EXISTS sensors (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            created_at TEXT NOT NULL,
            moisture REAL,
            temperature REAL,
            humidity REAL,
            ph REAL
        )
    """)

    connection.commit()
    connection.close()


def insert_sample(sample):
    connection = get_connection()
    cursor = connection.cursor()

    cursor.execute("""
        INSERT INTO samples (
            sample_name,
            sample_type,
            created_at,
            moisture,
            ph,
            temperature,
            humidity,
            quality_score,
            protein,
            fiber,
            energy,
            mould_risk,
            adulteration_risk,
            storage_risk,
            confidence,
            advisory,
            image_path
        )
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """, (
        sample["sample_name"],
        sample["sample_type"],
        sample["created_at"],
        sample["moisture"],
        sample["ph"],
        sample["temperature"],
        sample["humidity"],
        sample["quality_score"],
        sample["protein"],
        sample["fiber"],
        sample["energy"],
        sample["mould_risk"],
        sample["adulteration_risk"],
        sample["storage_risk"],
        sample["confidence"],
        json.dumps(sample["advisory"]),
        sample.get("image_path")
    ))

    sample_id = cursor.lastrowid

    connection.commit()
    connection.close()

    return sample_id


def get_all_samples():
    connection = get_connection()

    rows = connection.execute("""
        SELECT *
        FROM samples
        ORDER BY id DESC
    """).fetchall()

    connection.close()

    samples = []

    for row in rows:
        sample = dict(row)

        try:
            sample["advisory"] = json.loads(sample["advisory"])
        except:
            sample["advisory"] = []

        samples.append(sample)

    return samples


def get_sample(sample_id):
    connection = get_connection()

    row = connection.execute("""
        SELECT *
        FROM samples
        WHERE id = ?
    """, (sample_id,)).fetchone()

    connection.close()

    if row is None:
        return None

    sample = dict(row)

    try:
        sample["advisory"] = json.loads(sample["advisory"])
    except:
        sample["advisory"] = []

    return sample


def insert_sensor(data):
    connection = get_connection()

    cursor = connection.cursor()

    cursor.execute("""
        INSERT INTO sensors (
            created_at,
            moisture,
            temperature,
            humidity,
            ph
        )
        VALUES (?, ?, ?, ?, ?)
    """, (
        data["created_at"],
        data.get("moisture"),
        data.get("temperature"),
        data.get("humidity"),
        data.get("ph")
    ))

    connection.commit()
    connection.close()


def get_latest_sensor():
    connection = get_connection()

    row = connection.execute("""
        SELECT *
        FROM sensors
        ORDER BY id DESC
        LIMIT 1
    """).fetchone()

    connection.close()

    if row is None:
        return None

    return dict(row)