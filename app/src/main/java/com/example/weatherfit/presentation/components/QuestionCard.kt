package com.example.weatherfit.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherfit.domain.model.AnswerOption
import com.example.weatherfit.domain.model.Question
import com.example.weatherfit.domain.model.AnswerType

@Composable
fun QuestionCard(
    question: Question,
    onAnswerSelected: (AnswerOption) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 75.dp)
            .fillMaxWidth(0.8f)
            .wrapContentHeight()
            .clip(shape = RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.Transparent),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(question.text),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(15.dp))

            when (question.answerType) {
                AnswerType.CHOICE ->
                    question.options.forEach { option ->
                        var isClicked by remember { mutableStateOf(false) }

                        val buttonColor by animateColorAsState(
                            targetValue = if (isClicked) MaterialTheme.colorScheme.secondary
                                else MaterialTheme.colorScheme.primary,
                            label = "buttonColor"
                        )

                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(2.5.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = buttonColor
                            ),
                            onClick = {
                                isClicked = !isClicked
                                onAnswerSelected(option)
                            }
                        ) {
                            Text(
                                text = option.getValue(),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onPrimary,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                AnswerType.SLIDER -> TODO()
            }
        }
    }
}