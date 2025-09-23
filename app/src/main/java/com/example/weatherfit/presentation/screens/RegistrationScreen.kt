package com.example.weatherfit.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherfit.domain.model.Question
import com.example.weatherfit.presentation.components.QuestionPager
import com.example.weatherfit.presentation.theme.WeatherFitTheme

@Composable
fun RegistrationScreen(
    questions: List<Question>,
) {
    WeatherFitTheme (darkTheme = false) {
        QuestionPager(
            questions = questions,
            isItInitialSetup = true,
            onFinished = {

            }
        )
    }
}