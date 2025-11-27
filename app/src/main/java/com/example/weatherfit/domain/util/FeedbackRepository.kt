package com.example.weatherfit.domain.util

import com.example.weatherfit.R

enum class FeedbackRepository(val feedback: Feedback) {
    VERY_COLD(
        Feedback(
            image = R.drawable.dev_emoji_example,
            effectsOnColdSensitivity = -0.5f
        )
    ),
    COLD(
        Feedback(
            image = R.drawable.dev_emoji_example,
            effectsOnColdSensitivity = -0.25f
        )
    ),
    NORMAL(
        Feedback(
            image = R.drawable.dev_emoji_example,
            effectsOnColdSensitivity = 0f
        )
    ),
    WARM(
        Feedback(
            image = R.drawable.dev_emoji_example,
            effectsOnColdSensitivity = 0.25f
        )
    ),
    VERY_WARM(
        Feedback(
            image = R.drawable.dev_emoji_example,
            effectsOnColdSensitivity = 0.5f
        )
    ),
}