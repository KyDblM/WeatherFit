package com.example.weatherfit.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherfit.domain.model.AnswerOption
import com.example.weatherfit.domain.model.Question
import com.example.weatherfit.domain.model.QuestionSubject
import com.example.weatherfit.presentation.components.QuestionPager

@Composable
fun RegistrationScreen(
    paddingValues: PaddingValues,
    questions: List<Question>,
    onFinished: (Map<QuestionSubject, AnswerOption>) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(paddingValues.calculateTopPadding() + 10.dp))

        QuestionPager(
            modifier = Modifier.weight(1f),
            questions = questions,
            isItInitialSetup = true,
            onFinished = { answers ->
                onFinished(answers)
            },
        )

        Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding() + 10.dp))
    }
}