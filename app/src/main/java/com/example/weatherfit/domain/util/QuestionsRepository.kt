package com.example.weatherfit.domain.util

import com.example.weatherfit.R

class QuestionsRepository {
    val registrationQuestions: List<Question> = listOf(
        Question(
            subject = QuestionSubject.APP_THEME,
            text = R.string.first_initial_setup_question,
            options = listOf(
                AnswerOption.Theme(AppTheme.LIGHT),
                AnswerOption.Theme(AppTheme.DARK)
            ),
            answerType = AnswerType.CHOICE
        ),
        Question(
            subject = QuestionSubject.MANNEQUIN_GENDER,
            text = R.string.second_initial_setup_question,
            options = listOf(
                AnswerOption.Gender(MannequinGender.MALE),
                AnswerOption.Gender(MannequinGender.FEMALE)
            ),
            answerType = AnswerType.CHOICE
        )
    )

    val surveyQuestions: List<Question> = listOf(
        Question(
            subject = QuestionSubject.HOURS,
            text = R.string.hours_outside_question,
            answerType = AnswerType.SLIDER
        ),
        Question (
            subject = QuestionSubject.LOCATION,
            text = R.string.location_type_question,
            options = listOf(
                AnswerOption.Location(LocationType.INDOOR),
                AnswerOption.Location(LocationType.OUTDOOR)
            ),
            answerType = AnswerType.CHOICE
        ),
        Question (
            subject = QuestionSubject.ACTIVITY_MODE,
            text = R.string.activity_mode_question,
            options = listOf(
                AnswerOption.Activity(ActivityMode.REGULAR),
                AnswerOption.Activity(ActivityMode.SPORT)
            ),
            answerType = AnswerType.CHOICE
        )
    )
}