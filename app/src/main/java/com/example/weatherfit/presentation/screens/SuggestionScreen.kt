package com.example.weatherfit.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherfit.R
import com.example.weatherfit.domain.model.FitSuggestion
import com.example.weatherfit.presentation.components.FeedbackRow
import com.example.weatherfit.presentation.components.InformationBlock
import com.example.weatherfit.presentation.components.MannequinCard
import com.example.weatherfit.presentation.util.InformationBlockType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun SuggestionScreen(
    paddingValues: PaddingValues,
    suggestion: FitSuggestion,
    onClose: () -> Unit,
    onDelete: (FitSuggestion) -> Unit,
    onFeedbackClick: (FitSuggestion) -> Unit
) {
    val feedbackState = remember { mutableStateOf(suggestion.feedback) }

    Column(
        modifier = Modifier
            .padding(
                top = paddingValues.calculateTopPadding() + 20.dp,
                start = 20.dp,
                end = 20.dp,
                bottom = paddingValues.calculateBottomPadding() + 10.dp
            )
            .verticalScroll(
                state = rememberScrollState(),
                overscrollEffect = null
            ),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable(
                        onClick = { onClose() },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ),
                painter = painterResource(R.drawable.interface_arrow_icon),
                contentDescription = stringResource(R.string.weather_icon_description),
                tint = MaterialTheme.colorScheme.primary
            )

            val dateTime = LocalDateTime.parse(suggestion.time)
            val dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))
            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
            Text(
                modifier = Modifier.weight(4f),
                text = "${dateTime.format(dateFormatter)}, ${dateTime.format(timeFormatter)}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )

            Icon(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable(
                        onClick = { onDelete(suggestion) },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ),
                painter = painterResource(R.drawable.interface_trash_icon),
                contentDescription = stringResource(R.string.weather_icon_description),
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            MannequinCard(
                modifier = Modifier.weight(3f),
                mannequin = suggestion.mannequin,
            )

            if (!suggestion.mannequin.baseAccessories.isEmpty()) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    for (i in 1..suggestion.mannequin.baseAccessories.size) {
                        InformationBlock(
                            informationBlockType = InformationBlockType.OnlyImage(
                                image = suggestion.mannequin.baseAccessories[i - 1].accessory.image
                            )
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            InformationBlock(
                modifier = Modifier.weight(1f),
                informationBlockType = InformationBlockType.OnlyIcon(
                    icon = suggestion.weatherIcon
                )
            )

            InformationBlock(
                modifier = Modifier.weight(1f),
                informationBlockType = InformationBlockType.OnlyText(
                    text = suggestion.temperature.toString() + "°C",
                )
            )
        }

        if (feedbackState.value == null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(85.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(15.dp)
            ) {
                FeedbackRow(
                    onFeedbackClick = { feedback ->
                        feedbackState.value = feedback
                        suggestion.feedback = feedback
                        onFeedbackClick(suggestion)
                    }
                )
            }
        }
    }
}