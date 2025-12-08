package com.example.weatherfit.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weatherfit.R
import com.example.weatherfit.domain.model.FitSuggestion
import com.example.weatherfit.domain.util.Feedback
import com.example.weatherfit.presentation.components.ConfirmationDialog
import com.example.weatherfit.presentation.components.SuggestionHistoryItem

@Composable
fun HistoryScreen(
    paddingValues: PaddingValues,
    suggestions: MutableState<List<FitSuggestion>>,
    onSuggestionClick: (FitSuggestion) -> Unit,
    onFeedbackClick: (Feedback) -> Unit,
    onDeleteSuggestionsClick: (List<FitSuggestion>) -> Unit
) {
    val isDeleteDialogVisible = remember {mutableStateOf(false) }

    if (isDeleteDialogVisible.value) {
        ConfirmationDialog(
            confirmationText = stringResource(R.string.delete_all_suggestions_confirmation_text),
            onDismiss = { isDeleteDialogVisible.value = false },
            onConfirm = {
                onDeleteSuggestionsClick(suggestions.value)
                isDeleteDialogVisible.value = false
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding() + 20.dp,
                start = 20.dp,
                end = 20.dp
            ),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                modifier = Modifier.clickable(
                    onClick = {
                        isDeleteDialogVisible.value = true
                    },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ),
                painter = painterResource(R.drawable.interface_trash_icon),
                contentDescription = stringResource(R.string.delete_image_description),
                tint = MaterialTheme.colorScheme.primary
            )
        }

        if (suggestions.value.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(suggestions.value.size) { suggestion ->
                    SuggestionHistoryItem(
                        suggestion = suggestions.value[suggestion],
                        onSuggestionClick = {
                            onSuggestionClick(suggestions.value[suggestion])
                        },
                        onFeedbackClick = { feedback ->
                            onFeedbackClick(feedback)
                        }
                    )
                }

                item {
                    Spacer(Modifier.height(paddingValues.calculateBottomPadding() + 10.dp))
                }
            }
        }
    }
}