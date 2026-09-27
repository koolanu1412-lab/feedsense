package com.example.feedsense.ui.storage

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

data class LocalSampleRecord(
    val id: String,
    val type: String,
    val date: String,
    val status: String
)

class LocalStorage(context: Context) {

    private val prefs =
        context.getSharedPreferences(
            "feedsense_storage",
            Context.MODE_PRIVATE
        )

    fun saveLanguage(code: String) {
        prefs.edit()
            .putString("selected_language", code)
            .apply()
    }

    fun getLanguageCode(): String? {
        return prefs.getString("selected_language", null)
    }

    fun saveSample(
        type: String,
        status: String
    ) {

        val existing =
            prefs.getString("sample_history", "[]") ?: "[]"

        val array = JSONArray(existing)

        val record = JSONObject().apply {

            put(
                "id",
                "FS-" + System.currentTimeMillis()
            )

            put(
                "type",
                type
            )

            put(
                "date",
                System.currentTimeMillis()
            )

            put(
                "status",
                status
            )
        }

        array.put(record)

        prefs.edit()
            .putString(
                "sample_history",
                array.toString()
            )
            .apply()
    }

    fun getSamples(): List<LocalSampleRecord> {

        val existing =
            prefs.getString("sample_history", "[]") ?: "[]"

        val array = JSONArray(existing)

        val result = mutableListOf<LocalSampleRecord>()

        for (i in 0 until array.length()) {

            val item = array.getJSONObject(i)

            result.add(
                LocalSampleRecord(
                    id = item.getString("id"),
                    type = item.getString("type"),
                    date = item.getLong("date").toString(),
                    status = item.getString("status")
                )
            )
        }

        return result.reversed()
    }
}