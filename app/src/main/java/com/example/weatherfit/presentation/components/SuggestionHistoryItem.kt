package com.example.weatherfit.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherfit.R
import com.example.weatherfit.domain.model.FitSuggestion
import com.example.weatherfit.domain.util.Feedback
import com.example.weatherfit.domain.util.FeedbackRepository
import com.example.weatherfit.presentation.modifiers.customShadow
import com.example.weatherfit.presentation.theme.LightColorScheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun SuggestionHistoryItem(
    suggestion: FitSuggestion,
    onSuggestionClick: () -> Unit,
    onFeedbackClick: (Feedback) -> Unit
) {
    val shadowColor: Color = if (MaterialTheme.colorScheme == LightColorScheme) {
        MaterialTheme.colorScheme.secondary
    }
    else {
        MaterialTheme.colorScheme.background
    }

    val feedbackState = remember { mutableStateOf(suggestion.feedback) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(20.dp)
            .clickable(
                onClick = { onSuggestionClick() },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.5f),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                MannequinCard(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1.5f)
                        .customShadow (
                            shadowColor = shadowColor,
                            alpha = 0.4f,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    isItRectangular = false,
                    mannequin = suggestion.mannequin
                )

                val dateTime = LocalDateTime.parse(suggestion.time)
                val dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))
                val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
                Text(
                    modifier = Modifier.weight(4f),
                    text = "${dateTime.format(dateFormatter)}, ${dateTime.format(timeFormatter)}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.End
                )
            }

            if (feedbackState.value == null) {
                FeedbackRow(
                    modifier = Modifier.weight(1f),
                    onFeedbackClick = { feedback ->
                        feedbackState.value = feedback
                        onFeedbackClick(feedback)
                    }
                )
            }
        }

        if (feedbackState.value != null) {
            Image(
                modifier = Modifier.size(30.dp),
                painter = painterResource(suggestion.feedback!!.image),
                contentDescription = stringResource(R.string.feedback_image_description),
                contentScale = ContentScale.Fit
            )
        }
    }
}