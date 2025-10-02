package com.example.weatherfit.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherfit.R
import com.example.weatherfit.presentation.modifiers.customShadow
import com.example.weatherfit.presentation.navigation.NavigationItem
import com.example.weatherfit.presentation.theme.LightColorScheme

@Composable
fun CustomNavigationBar(
    navController: NavController,
    navigationItems: List<NavigationItem>,
    currentScreen: MutableState<String>,
    navigationBarPadding: Dp,
) {
    val navBarColor: Color
    val shadowColor: Color

    if (MaterialTheme.colorScheme == LightColorScheme) {
        navBarColor = MaterialTheme.colorScheme.secondary
        shadowColor = MaterialTheme.colorScheme.secondary
    }
    else {
        navBarColor = MaterialTheme.colorScheme.surface
        shadowColor = MaterialTheme.colorScheme.background
    }

    if (navigationItems.any { it.route == currentScreen.value }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    top = 10.dp,
                    start = 10.dp,
                    end = 10.dp,
                    bottom = navigationBarPadding + 10.dp
                )
                .customShadow(
                    shadowColor = shadowColor,
                    alpha = 0.4f,
                    shape = RoundedCornerShape(20.dp)
                )
                .background(
                    color = navBarColor,
                    shape = RoundedCornerShape(20.dp)
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navigationItems.forEach { navigationItem ->
                CustomNavigationBarItem(
                    modifier = Modifier.weight(1f),
                    currentScreen = currentScreen,
                    navigationItem = navigationItem,
                    navController = navController,
                )
            }
        }
    }
}

@Composable
fun CustomNavigationBarItem(
    modifier: Modifier = Modifier,
    currentScreen: MutableState<String>,
    navigationItem: NavigationItem,
    navController: NavController,
) {
    val selected = currentScreen.value == navigationItem.route
    val selectedNavigationColor: Color
    val unselectedNavigationColor: Color
    val animationDuration = 85

    if (MaterialTheme.colorScheme == LightColorScheme) {
        selectedNavigationColor = MaterialTheme.colorScheme.onPrimary
        unselectedNavigationColor = MaterialTheme.colorScheme.primary
    }
    else {
        selectedNavigationColor = MaterialTheme.colorScheme.onSurface
        unselectedNavigationColor = MaterialTheme.colorScheme.background
    }

    val iconColor = animateColorAsState(
        targetValue = if (selected) selectedNavigationColor else unselectedNavigationColor,
        animationSpec = tween(
            durationMillis = animationDuration,
            easing = LinearEasing
        ),
        label = "iconColorAnimation"
    )

    Box(
        modifier = modifier
            .wrapContentHeight()
            .background(Color.Transparent)
            .padding(vertical = 15.dp)
            .clickable(
                onClick = {
                    if (!selected) {
                        currentScreen.value = navigationItem.route
                        navController.navigate(navigationItem.route)
                    }
                },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        contentAlignment = Alignment.Center
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(navigationItem.image),
                contentDescription = stringResource(R.string.navigation_image_description),
                colorFilter = ColorFilter.tint(iconColor.value)
            )

            AnimatedVisibility(
                visible = selected,
                enter = fadeIn(
                    animationSpec = tween(
                        durationMillis = animationDuration,
                        easing = LinearEasing
                    )
                ),
                exit = fadeOut(
                    animationSpec = tween(
                        durationMillis = animationDuration,
                        easing = LinearEasing
                    )
                )
            ) {
                Text(
                    text = navigationItem.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = iconColor.value,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}