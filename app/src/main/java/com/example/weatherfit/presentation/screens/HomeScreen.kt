package com.example.weatherfit.presentation.screens

import androidx.compose.foundation.Image
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherfit.R
import com.example.weatherfit.domain.model.InformationBlockType
import com.example.weatherfit.presentation.components.InformationBlock
import com.example.weatherfit.presentation.components.MannequinCard

@Composable
fun HomeScreen(
    paddingValues: PaddingValues
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

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.fillMaxHeight(),
                text = stringResource(R.string.debug_city),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 26.sp,
                textAlign = TextAlign.Start
            )

            Row (
                modifier = Modifier.fillMaxHeight(),
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    modifier = Modifier.fillMaxHeight(),
                    painter = painterResource(R.drawable.sun_icon),
                    contentDescription = stringResource(R.string.information_block_image_description),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                )

                Text(
                    modifier = Modifier.fillMaxHeight(),
                    text = stringResource(R.string.debug_temperature),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 26.sp,
                    textAlign = TextAlign.End
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        MannequinCard()

        Spacer(modifier = Modifier.height(20.dp))

        Row (
            modifier = Modifier.fillMaxWidth()
        ) {
            InformationBlock(
                modifier = Modifier.weight(1f),
                informationBlockType = InformationBlockType.ImageAndText(
                    image = R.drawable.close_icon,
                    text = R.string.debug_info
                )
            )

            Spacer(modifier = Modifier.width(20.dp))

            InformationBlock(
                modifier = Modifier.weight(1f),
                informationBlockType = InformationBlockType.OnlyImage(
                    image = R.drawable.close_icon
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row (
            modifier = Modifier.fillMaxWidth()
        ) {
            InformationBlock(
                modifier = Modifier.weight(1f),
                informationBlockType = InformationBlockType.OnlyImage(
                    image = R.drawable.close_icon
                )
            )

            Spacer(modifier = Modifier.width(20.dp))

            InformationBlock(
                modifier = Modifier.weight(1f),
                informationBlockType = InformationBlockType.ImageAndText(
                    image = R.drawable.close_icon,
                    text = R.string.debug_info
                )
            )
        }


        Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding() + 10.dp))
    }
}