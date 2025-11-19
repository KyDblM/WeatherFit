package com.example.weatherfit.presentation.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.nativeCanvas

fun Modifier.customShadow(
    shadowColor: Color = Color.Black,
    alpha: Float = 0.5f,
    shadowRadius: Dp = 10.dp,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    shape: Shape,
): Modifier = this.then(
    Modifier.drawBehind {
        drawIntoCanvas { canvas ->
            val paint = Paint().asFrameworkPaint().apply {
                isAntiAlias = true
                this.color = android.graphics.Color.TRANSPARENT
                setShadowLayer(
                    shadowRadius.toPx(),
                    offsetX.toPx(),
                    offsetY.toPx(),
                    shadowColor.copy(alpha = alpha).toArgb()
                )
            }

            val path = when (val outline = shape.createOutline(size, layoutDirection, this)) {
                is Outline.Generic -> outline.path
                is Outline.Rounded -> Path().apply { addRoundRect(outline.roundRect) }
                is Outline.Rectangle -> Path().apply { addRect(outline.rect) }
            }

            canvas.nativeCanvas.drawPath(path.asAndroidPath(), paint)
        }
    }
)