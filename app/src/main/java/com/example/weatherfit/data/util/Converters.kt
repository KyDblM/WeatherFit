package com.example.weatherfit.data.util

import androidx.room.TypeConverter
import com.example.weatherfit.domain.util.Feedback
import com.example.weatherfit.domain.util.Mannequin
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    @TypeConverter
    fun fromMannequin(mannequin: Mannequin): String =
        Json.encodeToString(mannequin)

    @TypeConverter
    fun toMannequin(mannequinString: String): Mannequin =
        Json.decodeFromString(mannequinString)

    @TypeConverter
    fun fromFeedback(feedback: Feedback): String? =
        feedback?.let { Json.encodeToString(it) }

    @TypeConverter
    fun toFeedback(feedbackString: String?): Feedback? =
        feedbackString?.let { Json.decodeFromString<Feedback>(it) }
}