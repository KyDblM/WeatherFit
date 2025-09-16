package com.example.weatherfit.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherfit.domain.model.Question
import com.example.weatherfit.presentation.theme.WeatherFitTheme

@Preview()
@Composable
fun RegistrationScreen(
    questions: List<Question>? = emptyList()
) {
    WeatherFitTheme (darkTheme = false) {
        QuestionPager(questions)
    }
}