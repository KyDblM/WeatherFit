package com.example.weatherfit.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherfit.domain.util.Feedback
import com.example.weatherfit.domain.util.FeedbackRepository

@Composable
fun FeedbackRow(
    modifier: Modifier = Modifier,
    onFeedbackClick: (Feedback) -> Unit
) {
    Row (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        FeedbackRepository.entries.forEach { feedbackEntry ->
            FeedbackItem(
                modifier = Modifier.weight(1f),
                feedback = feedbackEntry.feedback,
                onFeedbackClick = { feedback ->
                    onFeedbackClick(feedback)
                }
            )
        }
    }
}