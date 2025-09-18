package com.example.weatherfit.domain.model

data class Question(
    val text: String,
    val options: List<AnswerOption> = emptyList(),
    val questionType: QuestionType,
)
