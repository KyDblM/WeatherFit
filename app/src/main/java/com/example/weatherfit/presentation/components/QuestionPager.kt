package com.example.weatherfit.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherfit.R
import com.example.weatherfit.domain.model.AnswerOption
import com.example.weatherfit.domain.model.AppTheme
import com.example.weatherfit.domain.model.Question

@Preview()
@Composable
fun QuestionPager(
    questions: List<Question>? = emptyList(),
) {
    val pagerState = rememberPagerState { 2 } // TODO вернуть questions.size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(R.drawable.close_icon),
                contentDescription = stringResource(R.string.close_button_description),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
        }

        HorizontalPager(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(0.8f)
                .background(Color.Transparent),
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { page ->
            //val question = questions[page]

            QuestionCard(
                Question(
                    text = stringResource(R.string.first_initial_setup_question),
                    options = listOf(
                        AnswerOption.Theme(AppTheme.LIGHT),
                        AnswerOption.Theme(AppTheme.DARK)
                    )
                ),
            )
        }

        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.secondary
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