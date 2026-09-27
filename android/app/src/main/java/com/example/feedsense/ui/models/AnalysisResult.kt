package com.example.feedsense.ui.models

data class AnalysisResult(
    val sampleId: String,

    val visibleDustRisk: String?,
    val visibleForeignParticleRisk: String?,
    val visibleMouldDetected: Boolean?,

    val protein: Double?,
    val moisture: Double?,
    val fiber: Double?,
    val energy: Double?,
    val mineralStatus: String?,

    val ureaRisk: String?,
    val silicaRisk: String?,
    val mycotoxinRisk: String?,
    val fungalRisk: String?,

    val ph: Double?,
    val fermentationQuality: String?,
    val spoilageRisk: String?,

    val qualityScore: Double?,
    val confidence: Double?,
    val advisory: String?,

    val analysisMode: String,
    val createdAt: String
)