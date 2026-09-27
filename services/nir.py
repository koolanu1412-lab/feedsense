import math


def generate_demo_spectrum():
    """
    Simulated NIR spectrum for the FeedSense prototype.
    This is demo data and does not come from real NIR hardware.
    """

    spectrum = []

    for wavelength in range(900, 1701, 20):

        absorbance = (
            0.45
            + 0.08 * math.sin(wavelength / 70)
            + 0.04 * math.cos(wavelength / 35)
        )

        spectrum.append({
            "wavelength": wavelength,
            "absorbance": round(absorbance, 4)
        })

    return {
        "status": "success",
        "mode": "demo",
        "message": "Simulated NIR spectrum generated",
        "spectrum": spectrum
    }