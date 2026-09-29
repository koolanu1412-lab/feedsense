package com.example.feedsense.ui.network

import android.graphics.Bitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets


data class ApiAnalysisResult(
    val sampleId: Int,
    val sampleName: String,
    val sampleType: String,
    val qualityScore: Double?,
    val protein: Double?,
    val moisture: Double?,
    val fiber: Double?,
    val energy: Double?,
    val mouldRisk: String?,
    val adulterationRisk: String?,
    val storageRisk: String?,
    val confidence: Double?,
    val advisory: List<String>,
    val mlStatus: String?,
    val model: String?,
    val mode: String?,
    val validated: Boolean,
    val analysisNote: String?,
    val imagePath: String?
)


object FeedSenseApi {

    private const val BASE_URL =
        "https://feedsense-itqj.onrender.com"

    suspend fun analyzeSample(
        sampleType: String,
        imageBitmap: Bitmap?
    ): ApiAnalysisResult = withContext(Dispatchers.IO) {

        val connection =
            URL("$BASE_URL/api/analyze").openConnection() as HttpURLConnection

        val boundary =
            "FeedSenseBoundary${System.currentTimeMillis()}"

        try {
            connection.requestMethod = "POST"
            connection.connectTimeout = 10000
            connection.readTimeout = 30000
            connection.doOutput = true
            connection.doInput = true

            connection.setRequestProperty(
                "Content-Type",
                "multipart/form-data; boundary=$boundary"
            )

            connection.setRequestProperty(
                "Accept",
                "application/json"
            )

            // Build the complete request body first.
            val body = ByteArrayOutputStream()

            fun writeText(value: String) {
                body.write(
                    value.toByteArray(StandardCharsets.UTF_8)
                )
            }

            // -------------------------------------------------
            // SAMPLE NAME
            // -------------------------------------------------

            writeText("--$boundary\r\n")
            writeText(
                "Content-Disposition: form-data; " +
                        "name=\"sample_name\"\r\n\r\n"
            )
            writeText("Mobile $sampleType Test\r\n")

            // -------------------------------------------------
            // SAMPLE TYPE
            // -------------------------------------------------

            writeText("--$boundary\r\n")
            writeText(
                "Content-Disposition: form-data; " +
                        "name=\"sample_type\"\r\n\r\n"
            )
            writeText("$sampleType\r\n")

            // -------------------------------------------------
            // IMAGE
            // -------------------------------------------------

            if (imageBitmap != null) {

                val imageBytes =
                    compressBitmap(imageBitmap)

                writeText("--$boundary\r\n")
                writeText(
                    "Content-Disposition: form-data; " +
                            "name=\"image\"; " +
                            "filename=\"feedsense_sample.jpg\"\r\n"
                )
                writeText(
                    "Content-Type: image/jpeg\r\n\r\n"
                )

                body.write(
                    imageBytes,
                    0,
                    imageBytes.size
                )

                writeText("\r\n")
            }

            // -------------------------------------------------
            // END MULTIPART BODY
            // -------------------------------------------------

            writeText("--$boundary--\r\n")

            connection.outputStream.use { output ->
                output.write(body.toByteArray())
                output.flush()
            }

            // -------------------------------------------------
            // RESPONSE CODE
            // -------------------------------------------------

            val responseCode =
                connection.responseCode

            // HTTP 400 = image rejected by visual screening.
            if (responseCode == 400) {
                throw Exception(
                    "Image rejected during visual screening. " +
                            "Please upload a clear feed or silage sample."
                )
            }

            if (responseCode !in 200..299) {
                throw Exception(
                    "Backend returned HTTP $responseCode"
                )
            }

            // -------------------------------------------------
            // SUCCESS RESPONSE
            // -------------------------------------------------

            val responseText =
                connection.inputStream
                    .bufferedReader()
                    .use {
                        it.readText()
                    }

            parseAnalysisResponse(responseText)

        } finally {
            connection.disconnect()
        }
    }

    // ---------------------------------------------------------
    // BITMAP → JPEG
    // ---------------------------------------------------------

    private fun compressBitmap(
        bitmap: Bitmap
    ): ByteArray {

        val output =
            ByteArrayOutputStream()

        bitmap.compress(
            Bitmap.CompressFormat.JPEG,
            85,
            output
        )

        return output.toByteArray()
    }

    // ---------------------------------------------------------
    // PARSE BACKEND RESPONSE
    // ---------------------------------------------------------

    private fun parseAnalysisResponse(
        responseText: String
    ): ApiAnalysisResult {

        val root =
            JSONObject(responseText)

        if (!root.optBoolean("success")) {
            throw Exception(
                root.optString(
                    "error",
                    "Analysis failed"
                )
            )
        }

        val result =
            root.getJSONObject("result")

        val mlAnalysis =
            result.optJSONObject("ml_analysis")

        val advisoryArray =
            result.optJSONArray("advisory")

        val advisory =
            mutableListOf<String>()

        if (advisoryArray != null) {
            for (
            index in 0 until advisoryArray.length()
            ) {
                advisory.add(
                    advisoryArray.optString(index)
                )
            }
        }

        return ApiAnalysisResult(

            sampleId =
                root.optInt(
                    "sample_id",
                    result.optInt("id", 0)
                ),

            sampleName =
                result.optString(
                    "sample_name",
                    "Unnamed Sample"
                ),

            sampleType =
                result.optString(
                    "sample_type",
                    "Feed"
                ),

            qualityScore =
                result.optDoubleOrNull(
                    "quality_score"
                ),

            protein =
                result.optDoubleOrNull(
                    "protein"
                ),

            moisture =
                result.optDoubleOrNull(
                    "moisture"
                ),

            fiber =
                result.optDoubleOrNull(
                    "fiber"
                ),

            energy =
                result.optDoubleOrNull(
                    "energy"
                ),

            mouldRisk =
                result.optStringOrNull(
                    "mould_risk"
                ),

            adulterationRisk =
                result.optStringOrNull(
                    "adulteration_risk"
                ),

            storageRisk =
                result.optStringOrNull(
                    "storage_risk"
                ),

            confidence =
                result.optDoubleOrNull(
                    "confidence"
                ),

            advisory =
                advisory,

            mlStatus =
                mlAnalysis?.optStringOrNull(
                    "status"
                ),

            model =
                mlAnalysis?.optStringOrNull(
                    "model"
                ),

            mode =
                mlAnalysis?.optStringOrNull(
                    "mode"
                ),

            validated =
                mlAnalysis?.optBoolean(
                    "validated",
                    false
                ) ?: false,

            analysisNote =
                result.optStringOrNull(
                    "analysis_note"
                ),

            imagePath =
                result.optStringOrNull(
                    "image_path"
                )
        )
    }
}


// -------------------------------------------------------------
// JSON HELPERS
// -------------------------------------------------------------

private fun JSONObject.optDoubleOrNull(
    key: String
): Double? {

    if (!has(key) || isNull(key)) {
        return null
    }

    val value =
        optDouble(
            key,
            Double.NaN
        )

    return if (value.isNaN()) {
        null
    } else {
        value
    }
}


private fun JSONObject.optStringOrNull(
    key: String
): String? {

    if (!has(key) || isNull(key)) {
        return null
    }

    val value =
        optString(
            key,
            ""
        )

    return value.ifBlank {
        null
    }
}