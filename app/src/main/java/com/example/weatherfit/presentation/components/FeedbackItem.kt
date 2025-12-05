package com.example.weatherfit.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weatherfit.R
import com.example.weatherfit.domain.util.Feedback
import com.example.weatherfit.presentation.modifiers.customShadow
import com.example.weatherfit.presentation.theme.LightColorScheme

@Composable
fun FeedbackItem(
    modifier: Modifier = Modifier,
    feedback: Feedback,
    onFeedbackClick: (Feedback) -> Unit
) {
    val shadowColor: Color = if (MaterialTheme.colorScheme == LightColorScheme) {
        MaterialTheme.colorScheme.secondary
    } else {
        MaterialTheme.colorScheme.background
    }

    Box(
        modifier = modifier
            .fillMaxHeight()
            .customShadow(
                shadowColor = shadowColor,
                alpha = 0.4f,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(shape = RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(5.dp)
            .clickable(
                onClick = { onFeedbackClick(feedback) },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        contentAlignment = Alignment.BottomEnd
    ) {
        Image(
            modifier = Modifier.matchParentSize(),
            painter = painterResource(feedback.image),
            contentDescription = stringResource(R.string.feedback_image_description),
            contentScale = ContentScale.Fit
        )
    }
}