from datetime import datetime

from services.storage import calculate_storage_risk


def safe_float(value, default=0):
    try:
        return float(value)
    except:
        return default


def analyze_sample(data, image_path=None):

    sample_name = data.get("sample_name", "Unnamed Sample")
    sample_type = data.get("sample_type", "Feed")

    moisture = safe_float(data.get("moisture"), 14.2)
    ph = safe_float(data.get("ph"), 5.8)
    temperature = safe_float(data.get("temperature"), 28)
    humidity = safe_float(data.get("humidity"), 65)

    # ---------------------------------------------------------
    # PROTOTYPE NUTRITION ESTIMATES
    # ---------------------------------------------------------

    protein = (
        11.5
        - 0.12 * max(moisture - 12, 0)
        + 0.15 * max(ph - 5, 0)
    )

    fiber = (
        17.5
        + 0.10 * max(moisture - 12, 0)
    )

    energy = (
        7.5
        - 0.03 * max(moisture - 12, 0)
    )

    protein = round(max(5, min(protein, 15)), 2)
    fiber = round(max(10, min(fiber, 25)), 2)
    energy = round(max(5, min(energy, 9)), 2)

    # ---------------------------------------------------------
    # STORAGE RISK
    # ---------------------------------------------------------

    storage_risk = calculate_storage_risk(
        moisture,
        temperature,
        humidity
    )

    # ---------------------------------------------------------
    # MOULD / SPOILAGE PROTOTYPE RISK
    # ---------------------------------------------------------

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

    # ---------------------------------------------------------
    # ADULTERATION PROTOTYPE RISK
    # ---------------------------------------------------------

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

    # ---------------------------------------------------------
    # QUALITY SCORE
    # ---------------------------------------------------------

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

    quality_score = max(0, min(100, quality_score))

    # ---------------------------------------------------------
    # CONFIDENCE
    # ---------------------------------------------------------

    confidence = 86

    if image_path:
        confidence += 2

    confidence = min(confidence, 95)

    # ---------------------------------------------------------
    # FARMER ADVISORY
    # ---------------------------------------------------------

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
            "Storage conditions are currently within the prototype safe range."
        )

    return {
        "sample_name": sample_name,
        "sample_type": sample_type,
        "created_at": datetime.now().isoformat(),

        "moisture": moisture,
        "ph": ph,
        "temperature": temperature,
        "humidity": humidity,

        "quality_score": quality_score,
        "protein": protein,
        "fiber": fiber,
        "energy": energy,

        "mould_risk": mould_risk,
        "adulteration_risk": adulteration_risk,
        "storage_risk": storage_risk,

        "confidence": confidence,

        "advisory": advisory,

        "visual_screening": {
            "status": "prototype",
            "message": "Prototype visual screening completed"
        },

        "analysis_note": (
            "Results are prototype/demo estimates and "
            "are not scientifically validated measurements."
        ),

        "image_path": image_path
    }