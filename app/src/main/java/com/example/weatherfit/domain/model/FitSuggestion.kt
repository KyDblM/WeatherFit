package com.example.weatherfit.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherfit.domain.util.Feedback
import com.example.weatherfit.domain.util.Mannequin
import kotlinx.serialization.Serializable

@Entity (tableName = "suggestions")
@Serializable
data class FitSuggestion(
    @PrimaryKey
    val time: String,
    val lifetime: Int,
    val mannequin: Mannequin,
    val weatherIcon: Int,
    val temperature: Int,
    val feedback: Feedback? = null
)