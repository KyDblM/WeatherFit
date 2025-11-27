package com.example.weatherfit.domain.model

import com.example.weatherfit.domain.util.Feedback
import com.example.weatherfit.domain.util.FeedbackRepository
import com.example.weatherfit.domain.util.Mannequin
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class FitSuggestion(
    val time: String,
    val lifetime: Int,
    val mannequin: Mannequin,
    val feedback: Feedback = FeedbackRepository.NORMAL.feedback
)
