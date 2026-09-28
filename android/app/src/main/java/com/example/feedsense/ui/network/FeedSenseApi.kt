package com.example.feedsense.ui.network

import android.graphics.Bitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


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
        "http://127.0.0.1:5000"


    suspend fun analyzeSample(
        sampleType: String,
        imageBitmap: Bitmap?
    ): ApiAnalysisResult = withContext(
        Dispatchers.IO
    ) {

        val url = URL(
            "$BASE_URL/api/analyze"
        )

        val connection =
            url.openConnection() as HttpURLConnection

        val boundary =
            "----FeedSenseBoundary${System.currentTimeMillis()}"

        try {

            connection.requestMethod = "POST"

            connection.connectTimeout = 10000
            connection.readTimeout = 30000

            connection.doOutput = true

            connection.setRequestProperty(
                "Content-Type",
                "multipart/form-data; boundary=$boundary"
            )

            connection.setRequestProperty(
                "Accept",
                "application/json"
            )

            connection.outputStream.use { output ->

                // -----------------------------------------
                // SAMPLE NAME
                // -----------------------------------------

                output.write(
                    "--$boundary\r\n".toByteArray()
                )

                output.write(
                    "Content-Disposition: form-data; name=\"sample_name\"\r\n\r\n"
                        .toByteArray()
                )

                output.write(
                    "Mobile $sampleType Test\r\n"
                        .toByteArray()
                )


                // -----------------------------------------
                // SAMPLE TYPE
                // -----------------------------------------

                output.write(
                    "--$boundary\r\n".toByteArray()
                )

                output.write(
                    "Content-Disposition: form-data; name=\"sample_type\"\r\n\r\n"
                        .toByteArray()
                )

                output.write(
                    "$sampleType\r\n"
                        .toByteArray()
                )


                // -----------------------------------------
                // IMAGE
                // -----------------------------------------

                if (imageBitmap != null) {

                    val imageBytes =
                        compressBitmap(imageBitmap)

                    output.write(
                        "--$boundary\r\n".toByteArray()
                    )

                    output.write(
                        (
                                "Content-Disposition: form-data; " +
                                        "name=\"image\"; " +
                                        "filename=\"feedsense_sample.jpg\"\r\n"
                                ).toByteArray()
                    )

                    output.write(
                        "Content-Type: image/jpeg\r\n\r\n"
                            .toByteArray()
                    )

                    output.write(imageBytes)

                    output.write(
                        "\r\n".toByteArray()
                    )
                }


                // -----------------------------------------
                // END REQUEST
                // -----------------------------------------

                output.write(
                    "--$boundary--\r\n".toByteArray()
                )
            }


            // ---------------------------------------------
            // RESPONSE
            // ---------------------------------------------

            val responseCode =
                connection.responseCode

            val inputStream =
                if (responseCode in 200..299) {
                    connection.inputStream
                } else {
                    connection.errorStream
                }

            val responseText =
                BufferedReader(
                    InputStreamReader(inputStream)
                ).use {
                    it.readText()
                }

            if (responseCode !in 200..299) {

                throw Exception(
                    "Backend returned HTTP $responseCode: $responseText"
                )
            }

            parseAnalysisResponse(
                responseText
            )

        } finally {

            connection.disconnect()
        }
    }


    private fun compressBitmap(
        bitmap: Bitmap
    ): ByteArray {

        val outputStream =
            ByteArrayOutputStream()

        bitmap.compress(
            Bitmap.CompressFormat.JPEG,
            80,
            outputStream
        )

        return outputStream.toByteArray()
    }


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
            result.optJSONObject(
                "ml_analysis"
            )

        val advisoryArray =
            result.optJSONArray(
                "advisory"
            )

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