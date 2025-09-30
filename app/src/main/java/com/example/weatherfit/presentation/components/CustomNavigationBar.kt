package com.example.weatherfit.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherfit.R
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
    val selectedNavigationColor: Color
    val unselectedNavigationColor: Color

    if (MaterialTheme.colorScheme == LightColorScheme) {
        navBarColor = MaterialTheme.colorScheme.secondary
        shadowColor = MaterialTheme.colorScheme.secondary
        selectedNavigationColor = MaterialTheme.colorScheme.onPrimary
        unselectedNavigationColor = MaterialTheme.colorScheme.primary
    }
    else {
        navBarColor = MaterialTheme.colorScheme.surface
        shadowColor = MaterialTheme.colorScheme.background
        selectedNavigationColor = MaterialTheme.colorScheme.onSurface
        unselectedNavigationColor = MaterialTheme.colorScheme.background
    }

    if (navigationItems.any { it.route == currentScreen.value }) {
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    top = 20.dp,
                    start = 20.dp,
                    end = 20.dp,
                    bottom = navigationBarPadding + 20.dp
                )
                .shadow(
                    elevation = 3.dp,
                    shape = RoundedCornerShape(16.dp),
                    spotColor = shadowColor
                ),
            containerColor = navBarColor,
            windowInsets = WindowInsets(0, 0, 0, 0)
        ) {
            navigationItems.forEach { navigationItem ->
                NavigationBarItem(
                    selected = currentScreen.value == navigationItem.route,
                    alwaysShowLabel = false,
                    label = {
                        Text(
                            text = navigationItem.title,
                            style = MaterialTheme.typography.titleLarge,
                            color = if (currentScreen.value == navigationItem.route)
                                selectedNavigationColor
                            else
                                unselectedNavigationColor,
                            textAlign = TextAlign.Center
                        )
                    },
                    icon = {
                        Image(
                            painter = painterResource(navigationItem.image),
                            contentDescription = stringResource(R.string.navigation_image_description),
                            colorFilter = ColorFilter.tint(
                                if (currentScreen.value == navigationItem.route)
                                    selectedNavigationColor
                                else
                                    unselectedNavigationColor
                            )
                        )
                    },
                    onClick = {
                        currentScreen.value = navigationItem.route
                        navController.navigate(navigationItem.route)
                    },
                    colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
                )
            }
        }
    }
}