package com.example.weatherfit.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherfit.R
import com.example.weatherfit.domain.model.AnswerOption
import com.example.weatherfit.domain.model.AppTheme
import com.example.weatherfit.domain.model.Question
import com.example.weatherfit.presentation.theme.WeatherFitTheme

@Preview()
@Composable
fun RegistrationScreen() {
    WeatherFitTheme (darkTheme = false) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            QuestionCard(
                Question(
                    text = stringResource(R.string.first_initial_setup_question),
                    options = listOf(
                        AnswerOption.Theme(AppTheme.LIGHT),
                        AnswerOption.Theme(AppTheme.DARK)
                    )
                ),
            )
        }
    }
}