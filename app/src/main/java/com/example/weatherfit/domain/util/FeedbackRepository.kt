package com.example.weatherfit.domain.util

import com.example.weatherfit.R

enum class FeedbackRepository(val feedback: Feedback) {
    VERY_COLD(
        Feedback(
            image = R.drawable.emoji_very_cold,
            effectsOnColdSensitivity = -0.5f
        )
    ),
    COLD(
        Feedback(
            image = R.drawable.emoji_cold,
            effectsOnColdSensitivity = -0.25f
        )
    ),
    NORMAL(
        Feedback(
            image = R.drawable.emoji_normal,
            effectsOnColdSensitivity = 0f
        )
    ),
    HOT(
        Feedback(
            image = R.drawable.emoji_hot,
            effectsOnColdSensitivity = 0.25f
        )
    ),
    VERY_HOT(
        Feedback(
            image = R.drawable.emoji_very_hot,
            effectsOnColdSensitivity = 0.5f
        )
    ),
}