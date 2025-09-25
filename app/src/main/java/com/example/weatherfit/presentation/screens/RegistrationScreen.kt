package com.example.weatherfit.presentation.screens

import androidx.compose.runtime.Composable
import com.example.weatherfit.domain.model.AnswerOption
import com.example.weatherfit.domain.model.Question
import com.example.weatherfit.domain.model.QuestionSubject
import com.example.weatherfit.presentation.components.QuestionPager

@Composable
fun RegistrationScreen(
    questions: List<Question>,
    onFinished: (Map<QuestionSubject, AnswerOption>) -> Unit
) {
    QuestionPager(
        questions = questions,
        isItInitialSetup = true,
        onFinished = { answers ->
            onFinished(answers)
        },
    )
}