from pathlib import Path
import json

import joblib
import numpy as np

from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import mean_absolute_error, r2_score
from sklearn.model_selection import train_test_split


BASE_DIR = Path(__file__).resolve().parent
MODEL_DIR = BASE_DIR / "models"

MODEL_DIR.mkdir(exist_ok=True)

RNG = np.random.default_rng(42)


# FeedSense current demo NIR range:
# 900 nm to 1700 nm, every 20 nm
NIR_WAVELENGTHS = list(range(900, 1701, 20))


FEATURE_NAMES = [
    "moisture",
    "ph",
    "temperature",
    "humidity",
]

FEATURE_NAMES.extend(
    [f"nir_{wavelength}" for wavelength in NIR_WAVELENGTHS]
)


def make_dataset(number_of_samples=2000):

    moisture = RNG.uniform(8, 28, number_of_samples)
    ph = RNG.uniform(4.0, 7.5, number_of_samples)
    temperature = RNG.uniform(18, 38, number_of_samples)
    humidity = RNG.uniform(35, 90, number_of_samples)

    # ---------------------------------------------------------
    # SYNTHETIC NIR SPECTRA
    # ---------------------------------------------------------

    x = np.linspace(
        0,
        1,
        len(NIR_WAVELENGTHS)
    )

    spectra = RNG.normal(
        0,
        0.003,
        (
            number_of_samples,
            len(NIR_WAVELENGTHS)
        )
    )

    # Moisture-related spectral pattern
    moisture_band = (
        0.08
        * (moisture / 20)[:, None]
        * np.exp(
            -(
                (
                    x[None, :] - 0.18
                ) / 0.10
            ) ** 2
        )
    )

    # Protein-related spectral pattern
    protein_latent = (
        8.5
        + 0.25 * ph
        - 0.08 * moisture
    )

    protein_band = (
        protein_latent[:, None]
        * 0.012
        * np.exp(
            -(
                (
                    x[None, :] - 0.43
                ) / 0.09
            ) ** 2
        )
    )

    # Fiber-related spectral pattern
    fiber_latent = (
        13.0
        + 0.22 * moisture
        - 0.05 * ph
    )

    fiber_band = (
        fiber_latent[:, None]
        * 0.009
        * np.exp(
            -(
                (
                    x[None, :] - 0.62
                ) / 0.12
            ) ** 2
        )
    )

    spectra = (
        spectra
        + moisture_band
        + protein_band
        + fiber_band
    )

    # ---------------------------------------------------------
    # SYNTHETIC TARGETS
    # ---------------------------------------------------------
    # These targets are DEMO relationships, not laboratory
    # measurements.
    # ---------------------------------------------------------

    protein = (
        8.5
        + 0.25 * ph
        - 0.08 * moisture
        + 0.02 * (100 - humidity)
        + RNG.normal(0, 0.20, number_of_samples)
    )

    fiber = (
        13.0
        + 0.22 * moisture
        - 0.05 * ph
        + RNG.normal(0, 0.30, number_of_samples)
    )

    energy = (
        8.2
        - 0.045 * moisture
        - 0.012 * fiber
        + 0.03 * protein
        + RNG.normal(0, 0.05, number_of_samples)
    )

    protein = np.clip(
        protein,
        5,
        20
    )

    fiber = np.clip(
        fiber,
        10,
        30
    )

    energy = np.clip(
        energy,
        5,
        9.5
    )

    X = np.column_stack(
        [
            moisture,
            ph,
            temperature,
            humidity,
            spectra,
        ]
    )

    y = np.column_stack(
        [
            protein,
            fiber,
            energy,
        ]
    )

    return X, y


def main():

    print("Generating synthetic FeedSense training data...")

    X, y = make_dataset()

    X_train, X_test, y_train, y_test = train_test_split(
        X,
        y,
        test_size=0.20,
        random_state=42
    )

    print("Training Random Forest model...")

    model = RandomForestRegressor(
        n_estimators=250,
        max_depth=14,
        random_state=42,
        n_jobs=-1
    )

    model.fit(
        X_train,
        y_train
    )

    predictions = model.predict(
        X_test
    )

    metrics = {

        "protein_mae":
            float(
                mean_absolute_error(
                    y_test[:, 0],
                    predictions[:, 0]
                )
            ),

        "protein_r2":
            float(
                r2_score(
                    y_test[:, 0],
                    predictions[:, 0]
                )
            ),

        "fiber_mae":
            float(
                mean_absolute_error(
                    y_test[:, 1],
                    predictions[:, 1]
                )
            ),

        "fiber_r2":
            float(
                r2_score(
                    y_test[:, 1],
                    predictions[:, 1]
                )
            ),

        "energy_mae":
            float(
                mean_absolute_error(
                    y_test[:, 2],
                    predictions[:, 2]
                )
            ),

        "energy_r2":
            float(
                r2_score(
                    y_test[:, 2],
                    predictions[:, 2]
                )
            ),

        "training_data":
            "synthetic_demo",

        "nir_range":
            "900-1700 nm, 20 nm spacing"
    }

    model_package = {

        "model": model,

        "feature_names":
            FEATURE_NAMES,

        "nir_wavelengths":
            NIR_WAVELENGTHS,

        "model_type":
            "RandomForestRegressor",

        "training_data":
            "synthetic_demo"
    }

    model_path = (
        MODEL_DIR
        / "nutrition_model.joblib"
    )

    metrics_path = (
        MODEL_DIR
        / "metrics.json"
    )

    joblib.dump(
        model_package,
        model_path
    )

    metrics_path.write_text(
        json.dumps(
            metrics,
            indent=2
        ),
        encoding="utf-8"
    )

    print()
    print("======================================")
    print("FeedSense ML model trained")
    print("======================================")
    print()
    print(
        f"Model: {model_path}"
    )
    print(
        f"Metrics: {metrics_path}"
    )
    print()
    print(
        json.dumps(
            metrics,
            indent=2
        )
    )
    print()
    print(
        "WARNING: This is a synthetic demo model."
    )
    print(
        "It is NOT laboratory validated."
    )


if __name__ == "__main__":
    main()
