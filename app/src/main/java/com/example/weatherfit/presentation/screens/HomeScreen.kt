package com.example.weatherfit.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherfit.R
import com.example.weatherfit.domain.model.FitSuggestion
import com.example.weatherfit.presentation.util.InformationBlockType
import com.example.weatherfit.domain.model.WeatherData
import com.example.weatherfit.presentation.components.InformationBlock
import com.example.weatherfit.presentation.components.LoadingIndicator
import com.example.weatherfit.presentation.components.MannequinCard

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    weatherData: MutableState<WeatherData?>,
    suggestion: MutableState<FitSuggestion?>,
    onMannequinClick: () -> Unit,
    onRetryClick: () -> Unit
) {

    if (weatherData.value != null) {
        val weather: WeatherData = weatherData.value!!

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .verticalScroll(
                    state = rememberScrollState(),
                    overscrollEffect = null
                )
        ) {
            Spacer(modifier = Modifier.height(paddingValues.calculateTopPadding() + 20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.65f),
                    text = weather.city,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Start
                )

                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.35f),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.fillMaxHeight(),
                        painter = painterResource(weather.weatherIcon),
                        contentDescription = stringResource(R.string.weather_icon_description),
                        tint = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = weather.temperature.toString() + "°C",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.End
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                MannequinCard(
                    modifier = Modifier.weight(3f),
                    mannequin = if (suggestion.value != null) suggestion.value!!.mannequin else null,
                    onClick = onMannequinClick
                )

                if (suggestion.value != null && !suggestion.value!!.mannequin.baseAccessories.isEmpty()) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        for (i in 1..suggestion.value!!.mannequin.baseAccessories.size) {
                            InformationBlock(
                                informationBlockType = InformationBlockType.OnlyImage(
                                    image = suggestion.value!!.mannequin.baseAccessories[i - 1].accessory.image
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                InformationBlock(
                    modifier = Modifier.weight(1f),
                    informationBlockType = InformationBlockType.TextWithTitleAndImage(
                        image = R.drawable.weather_wind_icon,
                        title = R.string.wind_title,
                        text = weather.windSpeed.toString() + " м/с"
                    )
                )

                Spacer(modifier = Modifier.width(20.dp))

                InformationBlock(
                    modifier = Modifier.weight(1f),
                    informationBlockType = InformationBlockType.TextWithTitleAndImage(
                        image = R.drawable.weather_humidity_icon,
                        title = R.string.humidity_title,
                        text = weather.humidity.toString() + " %"
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
                        image = R.drawable.weather_sun_icon,
                        title = R.string.uv_index_title,
                        text = weather.uvIndex.toString()
                    )
                )

                Spacer(modifier = Modifier.width(20.dp))

                InformationBlock(
                    modifier = Modifier.weight(1f),
                    informationBlockType = InformationBlockType.TextWithTitleAndImage(
                        image = R.drawable.weather_rain_icon,
                        title = R.string.rain_title,
                        text = weather.rainChance.toString() + " %"
                    )
                )
            }

            Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding() + 10.dp))
        }
    }
    else {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LoadingIndicator(onRetry = onRetryClick)
        }
    }
}