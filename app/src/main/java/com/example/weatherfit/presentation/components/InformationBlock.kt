package com.example.weatherfit.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherfit.R
import com.example.weatherfit.domain.model.InformationBlockType

@Composable
fun InformationBlock(
    modifier: Modifier,
    informationBlockType: InformationBlockType
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        when (informationBlockType) {
            is InformationBlockType.OnlyImage -> {
                Image(
                    painter = painterResource(informationBlockType.image),
                    contentDescription = stringResource(R.string.information_block_image_description),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                )
            }
            is InformationBlockType.ImageAndText -> {
                Row(
                    modifier = Modifier.matchParentSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier.weight(1f),
                        painter = painterResource(informationBlockType.image),
                        contentDescription = stringResource(R.string.information_block_image_description),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                    )

                    Text(
                        modifier = Modifier.weight(3f),
                        text = stringResource(informationBlockType.text),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}