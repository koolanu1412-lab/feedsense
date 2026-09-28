from pathlib import Path

import cv2


def analyze_image(image_path: str | None) -> dict:
    """
    Lightweight prototype visual screening for feed/silage images.

    This is NOT a trained feed classifier and is NOT laboratory validated.
    It is only a prototype screening layer for clearly unsuitable images.
    """

    # ---------------------------------------------------------
    # CHECK IMAGE PATH
    # ---------------------------------------------------------

    if not image_path:
        return {
            "status": "invalid",
            "message": "No sample image was uploaded.",
            "validated": False,
        }

    path = Path(image_path)

    if not path.exists():
        return {
            "status": "invalid",
            "message": "Uploaded image could not be found.",
            "validated": False,
        }

    # ---------------------------------------------------------
    # READ IMAGE
    # ---------------------------------------------------------

    image = cv2.imread(str(path))

    if image is None:
        return {
            "status": "invalid",
            "message": "The uploaded file is not a readable image.",
            "validated": False,
        }

    height, width = image.shape[:2]

    # ---------------------------------------------------------
    # BASIC RESOLUTION CHECK
    # ---------------------------------------------------------

    if width < 100 or height < 100:
        return {
            "status": "invalid",
            "message": "Image resolution is too low for visual screening.",
            "validated": False,
            "image_quality": "LOW",
        }

    # ---------------------------------------------------------
    # BASIC IMAGE QUALITY
    # ---------------------------------------------------------

    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

    brightness = float(gray.mean())
    contrast = float(gray.std())

    blur_score = float(
        cv2.Laplacian(gray, cv2.CV_64F).var()
    )

    # Very blurry image
    if blur_score < 20:
        return {
            "status": "invalid",
            "message": (
                "Image is too blurry. "
                "Capture a sharper close-up of the sample."
            ),
            "validated": False,
            "image_quality": "LOW",
        }

    # Very dark or very bright image
    if brightness < 25 or brightness > 235:
        return {
            "status": "invalid",
            "message": (
                "Lighting is unsuitable. "
                "Capture the sample in clear, even lighting."
            ),
            "validated": False,
            "image_quality": "LOW",
        }

    # ---------------------------------------------------------
    # CENTRAL SAMPLE REGION
    # ---------------------------------------------------------

    y1 = int(height * 0.15)
    y2 = int(height * 0.85)

    x1 = int(width * 0.15)
    x2 = int(width * 0.85)

    roi = image[y1:y2, x1:x2]

    if roi.size == 0:
        return {
            "status": "invalid",
            "message": "Unable to inspect the central sample region.",
            "validated": False,
        }

    # ---------------------------------------------------------
    # COLOR ANALYSIS
    # ---------------------------------------------------------

    hsv = cv2.cvtColor(roi, cv2.COLOR_BGR2HSV)

    # Green shades commonly seen in silage/plant material
    green_mask = cv2.inRange(
        hsv,
        (30, 40, 30),
        (95, 255, 255),
    )

    # Yellow/brown shades commonly seen in dry feed/material
    yellow_brown_mask = cv2.inRange(
        hsv,
        (8, 40, 25),
        (38, 255, 230),
    )

    target_mask = cv2.bitwise_or(
        green_mask,
        yellow_brown_mask,
    )

    target_color_ratio = float(
        (target_mask > 0).mean()
    )

    # ---------------------------------------------------------
    # TEXTURE / EDGE ANALYSIS
    # ---------------------------------------------------------

    roi_gray = cv2.cvtColor(
        roi,
        cv2.COLOR_BGR2GRAY,
    )

    edges = cv2.Canny(
        roi_gray,
        80,
        180,
    )

    edge_ratio = float(
        (edges > 0).mean()
    )

    # ---------------------------------------------------------
    # PROTOTYPE SCREENING SCORE
    # ---------------------------------------------------------

    screening_score = 0

    if contrast >= 20:
        screening_score += 1

    if target_color_ratio >= 0.18:
        screening_score += 2

    if edge_ratio >= 0.025:
        screening_score += 1

    if blur_score >= 50:
        screening_score += 1

    # ---------------------------------------------------------
    # REJECT UNSUITABLE IMAGE
    # ---------------------------------------------------------

    if screening_score < 4 or target_color_ratio < 0.12:
        return {
            "status": "invalid",
            "message": (
                "This image could not be verified as a suitable "
                "feed/silage sample. Please capture a clear close-up "
                "of the sample."
            ),
            "validated": False,
            "image_quality": "UNSUITABLE",
            "screening_score": screening_score,
            "target_color_ratio": round(
                target_color_ratio,
                4,
            ),
            "edge_ratio": round(
                edge_ratio,
                4,
            ),
        }

    # ---------------------------------------------------------
    # ACCEPT PROTOTYPE SCREENING
    # ---------------------------------------------------------

    return {
        "status": "prototype_cv",
        "message": (
            "Basic visual screening passed. "
            "This is a prototype screening result and is not "
            "laboratory validated."
        ),
        "validated": False,
        "image_quality": "ACCEPTABLE",
        "screening_score": screening_score,
        "target_color_ratio": round(
            target_color_ratio,
            4,
        ),
        "edge_ratio": round(
            edge_ratio,
            4,
        ),
        "brightness": round(
            brightness,
            2,
        ),
        "contrast": round(
            contrast,
            2,
        ),
        "blur_score": round(
            blur_score,
            2,
        ),
    }
