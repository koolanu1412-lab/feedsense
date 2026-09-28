from pathlib import Path

import cv2


def analyze_image(image_path: str | None) -> dict:
    """
    Prototype visual screening for feed/silage images.

    This is NOT a trained feed classifier and is NOT laboratory validated.
    It is only a screening layer to reject obviously unsuitable images.
    """

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

    image = cv2.imread(str(path))

    if image is None:
        return {
            "status": "invalid",
            "message": "The uploaded file is not a readable image.",
            "validated": False,
        }

    height, width = image.shape[:2]

    if width < 300 or height < 300:
        return {
            "status": "invalid",
            "message": (
                "Image resolution is too low. "
                "Capture a clear close-up of the feed or silage sample."
            ),
            "validated": False,
            "image_quality": "LOW",
        }

    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

    brightness = float(gray.mean())
    contrast = float(gray.std())
    blur_score = float(cv2.Laplacian(gray, cv2.CV_64F).var())

    if blur_score < 30:
        return {
            "status": "invalid",
            "message": (
                "Image is too blurry. "
                "Capture a sharper close-up of the sample."
            ),
            "validated": False,
            "image_quality": "LOW",
        }

    if brightness < 30 or brightness > 235:
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

    hsv = cv2.cvtColor(roi, cv2.COLOR_BGR2HSV)

    # ---------------------------------------------------------
    # COMMON FEED/SILAGE VISUAL COLORS
    # ---------------------------------------------------------

    green = cv2.inRange(
        hsv,
        (30, 45, 35),
        (95, 255, 255)
    )

    yellow_brown = cv2.inRange(
        hsv,
        (8, 45, 25),
        (38, 255, 230)
    )

    target_mask = cv2.bitwise_or(
        green,
        yellow_brown
    )

    target_ratio = float(
        (target_mask > 0).mean()
    )

    # ---------------------------------------------------------
    # TEXTURE CHECK
    # ---------------------------------------------------------

    roi_gray = cv2.cvtColor(
        roi,
        cv2.COLOR_BGR2GRAY
    )

    edges = cv2.Canny(
        roi_gray,
        80,
        180
    )

    edge_ratio = float(
        (edges > 0).mean()
    )

    # ---------------------------------------------------------
    # STRICT PROTOTYPE SCREENING
    # ---------------------------------------------------------

    screening_score = 0

    if contrast >= 20:
        screening_score += 1

    if target_ratio >= 0.18:
        screening_score += 2

    if edge_ratio >= 0.025:
        screening_score += 1

    if blur_score >= 50:
        screening_score += 1

    # Reject images that do not visually resemble a close-up
    # agricultural/feed sample strongly enough.
    if screening_score < 4 or target_ratio < 0.12:
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
            "target_color_ratio": round(target_ratio, 4),
            "edge_ratio": round(edge_ratio, 4),
        }

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
        "target_color_ratio": round(target_ratio, 4),
        "edge_ratio": round(edge_ratio, 4),
        "brightness": round(brightness, 2),
        "contrast": round(contrast, 2),
        "blur_score": round(blur_score, 2),
    }
