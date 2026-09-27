package com.example.feedsense.ui.models

data class SampleInput(
    val sampleType: String,
    val feedType: String? = null,
    val silageType: String? = null,

    val imageUri: String? = null,

    val spectrometerConnected: Boolean = false,
    val sensorsConnected: Boolean = false,

    val ph: Double? = null,
    val moisture: Double? = null,
    val temperature: Double? = null,
    val humidity: Double? = null
)