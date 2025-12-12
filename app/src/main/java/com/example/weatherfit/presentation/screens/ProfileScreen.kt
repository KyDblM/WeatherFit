package com.example.weatherfit.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weatherfit.R
import com.example.weatherfit.domain.util.AppTheme
import com.example.weatherfit.domain.util.MannequinGender
import com.example.weatherfit.presentation.components.ConfirmationDialog
import com.example.weatherfit.presentation.components.SettingButton

private const val BASE_COLD_SENSITIVITY = 0.5f

@Composable
fun ProfileScreen(
    paddingValues: PaddingValues,
    currentTheme: AppTheme,
    currentMannequinGender: MannequinGender,
    onThemeChange: (AppTheme) -> Unit,
    onMannequinTypeChange: (MannequinGender) -> Unit,
    onColdSensitivityReset: (Float) -> Unit
) {
    val otherTheme = if (currentTheme == AppTheme.LIGHT) AppTheme.DARK else AppTheme.LIGHT
    val otherMannequin = if (currentMannequinGender == MannequinGender.MALE) MannequinGender.FEMALE else MannequinGender.MALE

    val isThemeChangeDialogVisible = remember {mutableStateOf(false) }

    if (isThemeChangeDialogVisible.value) {
        ConfirmationDialog(
            confirmationText = stringResource(R.string.change_theme_confirmation_text)
                    + " \"${stringResource(otherTheme.label)}\"?",
            onDismiss = { isThemeChangeDialogVisible.value = false },
            onConfirm = {
                onThemeChange(otherTheme)
                isThemeChangeDialogVisible.value = false
            }
        )
    }

    val isMannequinChangeDialogVisible = remember {mutableStateOf(false) }

    if (isMannequinChangeDialogVisible.value) {
        ConfirmationDialog(
            confirmationText = stringResource(R.string.change_mannequin_confirmation_text)
                    + " \"${stringResource(otherMannequin.label)}\"?",
            onDismiss = { isMannequinChangeDialogVisible.value = false },
            onConfirm = {
                onMannequinTypeChange(otherMannequin)
                isMannequinChangeDialogVisible.value = false
            }
        )
    }

    val isResetDialogVisible = remember {mutableStateOf(false) }

    if (isResetDialogVisible.value) {
        ConfirmationDialog(
            confirmationText = stringResource(R.string.reset_cold_sensitivity_confirmation_text),
            onDismiss = { isResetDialogVisible.value = false },
            onConfirm = {
                onColdSensitivityReset(BASE_COLD_SENSITIVITY)
                isResetDialogVisible.value = false
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding() + 20.dp,
                start = 20.dp,
                end = 20.dp,
                bottom = paddingValues.calculateBottomPadding() + 10.dp
            ),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SettingButton(
            text = stringResource(R.string.change_theme_button),
            onClick = {
                isThemeChangeDialogVisible.value = true
            }
        )

        SettingButton(
            text = stringResource(R.string.change_mannequin_button),
            onClick = {
                isMannequinChangeDialogVisible.value = true
            }
        )

        SettingButton(
            text = stringResource(R.string.reset_cold_sensitivity_button),
            onClick = {
                isResetDialogVisible.value = true
            }
        )
    }
}