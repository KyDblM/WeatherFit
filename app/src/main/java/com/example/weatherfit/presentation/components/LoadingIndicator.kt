package com.example.weatherfit.presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun LoadingIndicator() {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 900, // скорость вращения (меньше = быстрее)
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
            .rotate(rotation)) {
        val radius = size.minDimension / 2
        val colorList = listOf(startIndicatorColor, endIndicatorColor)
        val brush = Brush.horizontalGradient(colorList)
        drawArc(
            brush = brush,
            startAngle = 0f,
            sweepAngle = 180f, // длина дуги
            useCenter = false,
            topLeft = center - Offset(radius, radius),
            size = Size(radius * 2, radius * 2),
            style = Stroke(width = 10f, cap = StrokeCap.Round)
        )
    }
}