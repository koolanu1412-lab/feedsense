from pathlib import Path

import cv2


def analyze_image(image_path: str | None) -> dict:
    """
    Lightweight prototype visual screening.

    This is NOT a trained feed/silage classifier and must not be
    treated as laboratory validation.
    """

    if not image_path:
        return {
            "status": "not_available",
            "message": "No image was uploaded.",
            "validated": False,
        }

    path = Path(image_path)

    if not path.exists():
        return {
            "status": "not_available",
            "message": "Uploaded image could not be found.",
            "validated": False,
        }

    image = cv2.imread(str(path))

    if image is None:
        return {
            "status": "invalid",
            "message": "The uploaded file is not a readable image.",
            "validated": False,
        }

    height, width = image.shape[:2]

    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

    brightness = float(gray.mean())
    contrast = float(gray.std())

    blur_score = float(cv2.Laplacian(gray, cv2.CV_64F).var())

    edges = cv2.Canny(gray, 100, 200)
    edge_ratio = float((edges > 0).mean())

    hsv = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)
    saturation = float(hsv[:, :, 1].mean())

    if width < 200 or height < 200:
        return {
            "status": "invalid",
            "message": "Image resolution is too low. Capture a closer sample image.",
            "validated": False,
            "image_quality": "LOW",
        }

    if blur_score < 20:
        return {
            "status": "invalid",
            "message": "Image is too blurry for visual screening. Capture a clearer sample.",
            "validated": False,
            "image_quality": "LOW",
            "blur_score": round(blur_score, 2),
        }

    if brightness < 25 or brightness > 235:
        return {
            "status": "invalid",
            "message": "Image lighting is unsuitable. Capture the sample in better lighting.",
            "validated": False,
            "image_quality": "LOW",
        }

    suitability_score = 0

    if 50 <= brightness <= 210:
        suitability_score += 1

    if contrast >= 25:
        suitability_score += 1

    if blur_score >= 50:
        suitability_score += 1

    if edge_ratio >= 0.02:
        suitability_score += 1

    if saturation >= 35:
        suitability_score += 1

    if suitability_score < 3:
        return {
            "status": "invalid",
            "message": (
                "The image does not provide enough visual evidence for "
                "feed/silage screening. Capture a clear close-up of the sample."
            ),
            "validated": False,
            "image_quality": "UNSUITABLE",
            "suitability_score": suitability_score,
        }

    return {
        "status": "prototype_cv",
        "message": (
            "Basic visual screening passed. "
            "This is a prototype screening result and is not laboratory validated."
        ),
        "validated": False,
        "image_quality": "ACCEPTABLE",
        "suitability_score": suitability_score,
        "brightness": round(brightness, 2),
        "contrast": round(contrast, 2),
        "blur_score": round(blur_score, 2),
        "edge_ratio": round(edge_ratio, 4),
        "saturation": round(saturation, 2),
    }
