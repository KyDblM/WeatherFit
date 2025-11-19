package com.example.weatherfit.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weatherfit.R
import com.example.weatherfit.domain.util.Mannequin

private const val squareRatio = 1f
private const val rectangularRatio = 0.65f
private const val arrowRotateAngle = 45f

@Composable
fun MannequinCard(
    modifier: Modifier = Modifier,
    isItRectangular: Boolean = true,
    mannequin: MutableState<Mannequin?>
) {
    MannequinCardBase(modifier, false, isItRectangular, mannequin, null)
}

@Composable
fun MannequinCard(
    modifier: Modifier = Modifier,
    isItRectangular: Boolean = true,
    mannequin: MutableState<Mannequin?>,
    onClick: () -> Unit
) {
    MannequinCardBase(modifier, true, isItRectangular, mannequin, onClick)
}

@Composable
private fun MannequinCardBase(
    modifier: Modifier,
    isItClickable: Boolean,
    isItRectangular: Boolean,
    mannequin: MutableState<Mannequin?>,
    onClick: (() -> Unit)?
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .aspectRatio(if (isItRectangular) rectangularRatio else squareRatio)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(20.dp)
            .then(
                if (isItClickable) {
                    Modifier.clickable(
                        onClick = { onClick!!() },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
                }
                else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.BottomEnd
    ) {
        if (mannequin.value != null) {
            Image(
                modifier = Modifier.matchParentSize(),
                painter = painterResource(mannequin.value!!.image),
                contentDescription = stringResource(R.string.mannequin_image_description),
                contentScale = ContentScale.Fit
            )
        }
        else {
            Image(
                modifier = Modifier.matchParentSize(),
                painter = painterResource(R.drawable.dev_naked_mannequin_example),
                contentDescription = stringResource(R.string.mannequin_image_description),
                contentScale = ContentScale.Fit
            )
        }


        if (isItClickable) {
            Icon(
                modifier = Modifier.rotate(arrowRotateAngle),
                painter = painterResource(R.drawable.interface_arrow_icon),
                contentDescription = stringResource(R.string.arrow_image_description),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}