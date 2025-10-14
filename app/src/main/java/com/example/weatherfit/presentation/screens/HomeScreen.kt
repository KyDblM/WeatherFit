package com.example.weatherfit.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherfit.R
import com.example.weatherfit.domain.model.InformationBlockType
import com.example.weatherfit.presentation.components.InformationBlock
import com.example.weatherfit.presentation.components.MannequinCard

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .verticalScroll(
                state = rememberScrollState(),
                overscrollEffect = null
            )
    ) {
        Spacer(modifier = Modifier.height(paddingValues.calculateTopPadding() + 10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.fillMaxHeight(),
                text = stringResource(R.string.debug_city),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Start
            )

            Row(
                modifier = Modifier.fillMaxHeight(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.fillMaxHeight(),
                    painter = painterResource(R.drawable.sun_icon),
                    contentDescription = stringResource(R.string.information_block_image_description),
                    tint = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = stringResource(R.string.debug_temperature),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.End
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        MannequinCard()

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            InformationBlock(
                modifier = Modifier.weight(1f),
                informationBlockType = InformationBlockType.TextWithTitleAndImage(
                    image = R.drawable.wind_icon,
                    title = R.string.wind_title,
                    text = R.string.debug_wind
                )
            )

            Spacer(modifier = Modifier.width(20.dp))

            InformationBlock(
                modifier = Modifier.weight(1f),
                informationBlockType = InformationBlockType.TextWithTitleAndImage(
                    image = R.drawable.humidity_icon,
                    title = R.string.humidity_title,
                    text = R.string.debug_humidity
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            InformationBlock(
                modifier = Modifier.weight(1f),
                informationBlockType = InformationBlockType.TextWithTitleAndImage(
                    image = R.drawable.sun_icon,
                    title = R.string.uv_index_title,
                    text = R.string.debug_uv_index
                )
            )

            Spacer(modifier = Modifier.width(20.dp))

            InformationBlock(
                modifier = Modifier.weight(1f),
                informationBlockType = InformationBlockType.TextWithTitleAndImage(
                    image = R.drawable.rain_icon,
                    title = R.string.rain_title,
                    text = R.string.debug_rain
                )
            )
        }


        Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding() + 10.dp))
    }
}