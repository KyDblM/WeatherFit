package com.example.weatherfit.presentation.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import kotlin.math.roundToInt

private const val startHour = 1f
private const val endHour = 24f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HourSlider(
    selectedValue: MutableFloatState
) {
    val trackColor = MaterialTheme.colorScheme.surfaceDim
    val thumbColor = MaterialTheme.colorScheme.primary
    val textColor = MaterialTheme.colorScheme.onPrimary
    val smallText = MaterialTheme.typography.titleSmall
    val largeText = MaterialTheme.typography.titleLarge

    val isDragging = remember { mutableStateOf(false) }

    Slider(
        modifier = Modifier.height(100.dp),
        value = selectedValue.floatValue,
        valueRange = startHour..endHour,
        steps = 23,
        onValueChange = {
            selectedValue.floatValue = it
            isDragging.value = true
        },
        onValueChangeFinished = {
            isDragging.value = false
        },
        thumb = {
            val animationProgress = animateFloatAsState(
                targetValue = if (!isDragging.value) 0f else 1f,
                animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
            )

            val textMeasurer = rememberTextMeasurer()
            val fontSize = lerp(smallText.fontSize, largeText.fontSize, animationProgress.value)

            Canvas(
                modifier = Modifier
                    .size(60.dp)
            ) {
                val radius = size.minDimension / 4
                val center = Offset(size.width / 2, size.height / 2)
                val c = 0.5522848f * radius

                val path = Path().apply {
                    moveTo(center.x, center.y - radius - (radius * 3f * animationProgress.value))

                    cubicTo(
                        center.x + c + (2f * radius * animationProgress.value), center.y - radius - (radius * 2.9f * animationProgress.value),
                        center.x + radius + (radius * animationProgress.value), center.y - c - (radius * 0.75f * animationProgress.value),
                        center.x + radius + (radius * 0.175f * animationProgress.value), center.y
                    )

                    cubicTo(
                        center.x + radius, center.y + c,
                        center.x + c, center.y + radius,
                        center.x, center.y + radius
                    )

                    cubicTo(
                        center.x - c, center.y + radius,
                        center.x - radius, center.y + c,
                        center.x - radius - (radius * 0.175f * animationProgress.value), center.y
                    )

                    cubicTo(
                        center.x - radius - (radius * animationProgress.value), center.y - c - (radius * 0.75f * animationProgress.value),
                        center.x - c - (2f * radius * animationProgress.value), center.y - radius - (radius * 2.9f * animationProgress.value),
                        center.x, center.y - radius - (radius * 3f * animationProgress.value)
                    )

                    close()
                }

                drawPath(
                    path = path,
                    color = thumbColor
                )

                val thumbText = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = fontSize,
                            color = textColor,
                            fontWeight = FontWeight.Bold
                        )
                    ) { append(selectedValue.floatValue.roundToInt().toString())  }
                }

                val textLayoutResult = textMeasurer.measure(thumbText)
                val textWidth = textLayoutResult.size.width.toFloat()
                val textHeight = textLayoutResult.size.height.toFloat()

                val textOffset = Offset(
                    x = center.x - textWidth / 2,
                    y = center.y - textHeight / 2 - (radius * 2.5f * animationProgress.value)
                )

                drawText(
                    textMeasurer = textMeasurer,
                    text = thumbText,
                    topLeft = textOffset
                )
            }
        },
        track = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .background(
                        color = trackColor,
                        shape = CircleShape
                    )
            )
        }
    )
}