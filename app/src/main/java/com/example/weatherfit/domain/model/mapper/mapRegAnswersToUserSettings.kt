package com.example.weatherfit.domain.model.mapper

import android.content.Context
import com.example.weatherfit.domain.model.AnswerOption
import com.example.weatherfit.domain.model.QuestionSubject
import com.example.weatherfit.domain.model.UserSettings

fun mapRegAnswersToUserSettings(answers: Map<QuestionSubject, AnswerOption>, context: Context) : UserSettings {
    val mannequinType = context.getString((answers[QuestionSubject.MANNEQUIN_GENDER] as AnswerOption.Gender).value.label)
    val theme = context.getString((answers[QuestionSubject.APP_THEME] as AnswerOption.Theme).value.label)

    return UserSettings(
        mannequinType = mannequinType,
        theme = theme,
    )
}