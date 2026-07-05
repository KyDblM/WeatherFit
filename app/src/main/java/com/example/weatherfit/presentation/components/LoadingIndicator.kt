package com.example.weatherfit.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherfit.R

@Composable
fun LoadingIndicator(
    onRetry: () -> Unit
) {
    var isClicked by remember { mutableStateOf(false) }
    val buttonColor by animateColorAsState(
        targetValue = if (isClicked) MaterialTheme.colorScheme.secondary
        else MaterialTheme.colorScheme.primary,
        label = "buttonColor"
    )

    var isLoading by remember { mutableStateOf(true) }
    LaunchedEffect(isLoading) {
        if (isLoading) {
            kotlinx.coroutines.delay(7500)
            isLoading = false
        }
    }
    if (!isLoading) {
        Button(
            modifier = Modifier
                .wrapContentWidth()
                .padding(2.5.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor
            ),
            onClick = {
                isLoading = true
                onRetry()
            }
        ) {
            Text(
                text = stringResource(R.string.retry_button),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center
            )
        }
    }
    else {
        val infiniteTransition = rememberInfiniteTransition()
        val rotation by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 900,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Restart
            )
        )

        val startIndicatorColor = MaterialTheme.colorScheme.primary
        val endIndicatorColor = MaterialTheme.colorScheme.background

        Canvas(
            modifier = Modifier
                .size(50.dp)
                .rotate(rotation)
        ) {
            val radius = size.minDimension / 2
            val colorList = listOf(startIndicatorColor, endIndicatorColor)
            val brush = Brush.horizontalGradient(colorList)
            drawArc(
                brush = brush,
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = false,
                topLeft = center - Offset(radius, radius),
                size = Size(radius * 2, radius * 2),
                style = Stroke(width = 10f, cap = StrokeCap.Round)
            )
        }
    }
}