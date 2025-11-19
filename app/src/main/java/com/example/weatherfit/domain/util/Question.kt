package com.example.weatherfit.domain.util

import androidx.annotation.StringRes

data class Question(
    val subject: QuestionSubject,
    @StringRes val text: Int,
    val options: List<AnswerOption> = emptyList(),
    val answerType: AnswerType,
)
