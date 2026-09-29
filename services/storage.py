def calculate_storage_risk(moisture, temperature, humidity):
    """
    Prototype rule-based storage risk engine.
    This is a demo estimate, not a scientifically validated model.
    """

    score = 0

    if moisture >= 18:
        score += 3
    elif moisture >= 14:
        score += 2
    else:
        score += 1

    if temperature >= 30:
        score += 3
    elif temperature >= 25:
        score += 2
    else:
        score += 1

    if humidity >= 75:
        score += 3
    elif humidity >= 65:
        score += 2
    else:
        score += 1

    if score >= 8:
        risk = "HIGH"
    elif score >= 5:
        risk = "MEDIUM"
    else:
        risk = "LOW"

    return risk