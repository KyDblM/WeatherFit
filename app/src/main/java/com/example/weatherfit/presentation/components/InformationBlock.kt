package com.example.weatherfit.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherfit.R
import com.example.weatherfit.domain.model.InformationBlockType

@Composable
fun InformationBlock(
    modifier: Modifier = Modifier,
    informationBlockType: InformationBlockType
) {
    val iconHeight = with(LocalDensity.current) {
        MaterialTheme.typography.titleSmall.fontSize.toDp()
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(15.dp),
        contentAlignment = Alignment.Center
    ) {
        when (informationBlockType) {
            is InformationBlockType.OnlyImage -> {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(informationBlockType.image),
                    contentDescription = stringResource(R.string.information_block_image_description),
                    contentScale = ContentScale.Crop
                )
            }

            is InformationBlockType.TextWithTitleAndImage -> {
                Column(
                    modifier = Modifier.matchParentSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            modifier = Modifier
                                .height(iconHeight)
                                .aspectRatio(1f),
                            painter = painterResource(informationBlockType.image),
                            contentDescription = stringResource(R.string.information_block_image_description),
                            tint = MaterialTheme.colorScheme.surfaceDim
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 2.dp),
                            text = stringResource(informationBlockType.title),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.surfaceDim,
                            textAlign = TextAlign.Start
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(5f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
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
}