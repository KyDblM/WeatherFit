package com.example.weatherfit.domain.model.mapper

import com.example.weatherfit.domain.model.AnswerOption
import com.example.weatherfit.domain.model.QuestionSubject
import com.example.weatherfit.domain.model.UserSettings

fun mapRegAnswersToUserSettings(answers: Map<QuestionSubject, AnswerOption>) : UserSettings {
    val mannequinType = (answers[QuestionSubject.MANNEQUIN_GENDER] as AnswerOption.Gender).value.label.toString()
    val theme = (answers[QuestionSubject.APP_THEME] as AnswerOption.Theme).value.label.toString()

    return UserSettings(
        mannequinType = mannequinType,
        theme = theme,
    )
}