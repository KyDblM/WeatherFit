package com.example.weatherfit.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weatherfit.R
import com.example.weatherfit.domain.util.AnswerOption
import com.example.weatherfit.domain.util.Question
import com.example.weatherfit.domain.util.QuestionSubject
import kotlinx.coroutines.launch

@Composable
fun QuestionPager(
    modifier: Modifier = Modifier,
    questions: List<Question>,
    isItInitialSetup: Boolean,
    onFinished: (Map<QuestionSubject, AnswerOption>) -> Unit,
    onCloseClick: () -> Unit
) {
    val pagerState = rememberPagerState { questions.size }
    val coroutineScope = rememberCoroutineScope()
    val answers = remember { mutableStateMapOf<QuestionSubject, AnswerOption>() }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!isItInitialSetup) {
            Row(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(R.drawable.interface_close_icon),
                    contentDescription = stringResource(R.string.close_button_description),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    modifier = Modifier.clickable(
                        onClick = { onCloseClick() },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
                )
            }
        }

        HorizontalPager(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Transparent),
            state = pagerState,
            verticalAlignment = Alignment.Top,
            userScrollEnabled = false,
        ) { page ->

            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.Center
            ) {
                QuestionCard(
                    question = questions[page],
                    onAnswerSelected = { answer ->
                        answers[questions[page].subject] = answer
                        if (page == questions.lastIndex) {
                            onFinished(answers)
                        } else {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(
                                    page + 1,
                                    animationSpec = tween(
                                        durationMillis = 500,
                                        easing = FastOutSlowInEasing
                                    )
                                )
                            }
                        }
                    }
                )
            }
        }

        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { currentPage ->
                val targetColor = if (pagerState.currentPage == currentPage) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.secondary

                val color by animateColorAsState(
                    targetValue = targetColor,
                    label = "indicatorColor"
                )

                Box(
                    modifier = Modifier
                        .padding(3.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(11.dp)
                )
            }
        }
    }
}