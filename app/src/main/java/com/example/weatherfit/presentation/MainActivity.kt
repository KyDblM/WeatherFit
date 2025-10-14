package com.example.weatherfit.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.weatherfit.R
import com.example.weatherfit.domain.repository.QuestionsRepository
import com.example.weatherfit.presentation.components.CustomNavigationBar
import com.example.weatherfit.presentation.navigation.NavigationRoutes
import com.example.weatherfit.presentation.screens.HomeScreen
import com.example.weatherfit.presentation.screens.RegistrationScreen
import com.example.weatherfit.presentation.theme.WeatherFitTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        window.navigationBarColor = android.graphics.Color.TRANSPARENT
        window.isNavigationBarContrastEnforced = false
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.auto(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            )
        )

        setContent {
            WeatherFitTheme (viewModel.isDarkTheme.value) {
                val view = LocalView.current
                val window = (view.context as? Activity)?.window
                val isDark = viewModel.isDarkTheme.value ?: isSystemInDarkTheme()
                SideEffect {
                    window?.let { window ->
                        val controller = WindowCompat.getInsetsController(window, view)
                        controller.isAppearanceLightStatusBars = !isDark
                        controller.isAppearanceLightNavigationBars = !isDark
                    }
                }

                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentScreen = navBackStackEntry?.destination?.route ?: viewModel.startScreen

                Scaffold(
                    bottomBar = {
                        if (viewModel.navigationItems.any {it.route == currentScreen}) {
                            CustomNavigationBar(
                                navController = navController,
                                navigationItems = viewModel.navigationItems,
                                currentScreen = currentScreen,
                                navigationBarPadding = WindowInsets
                                    .navigationBars
                                    .only(WindowInsetsSides.Bottom)
                                    .asPaddingValues()
                                    .calculateBottomPadding()
                            )
                        }
                    }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = viewModel.startScreen,
                        enterTransition = { fadeIn(animationSpec = tween(delayMillis = 1, durationMillis = 1)) },
                        exitTransition = { fadeOut(animationSpec = tween(durationMillis = 1)) },
                        popEnterTransition = { fadeIn(animationSpec = tween(delayMillis = 1, durationMillis = 1)) },
                        popExitTransition = { fadeOut(animationSpec = tween(durationMillis = 1)) }
                    ) {
                        composable(route = NavigationRoutes.Registration.route) {
                            RegistrationScreen(
                                paddingValues = paddingValues,
                                questions = QuestionsRepository().registrationQuestions,
                                onFinished = { answers ->
                                    viewModel.saveSettings(answers)
                                    viewModel.updateAppTheme(viewModel.getAppTheme())

                                    navController.navigate("home_screen")
                                }
                            )
                        }

                        composable(route = NavigationRoutes.Home.route) {
                            HomeScreen(paddingValues = paddingValues)
                        }

                        composable(route = NavigationRoutes.History.route) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(MaterialTheme.colorScheme.background),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Top,
                            ) {
                                Spacer(modifier = Modifier.height(paddingValues.calculateTopPadding() + 20.dp))
                                Text(text = stringResource(R.string.navigation_title_history))
                                Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding() + 20.dp))
                            }
                        }

                        composable(route = NavigationRoutes.Profile.route) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(MaterialTheme.colorScheme.surface),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Bottom,
                            ) {
                                Spacer(modifier = Modifier.height(paddingValues.calculateTopPadding() + 20.dp))
                                Text(text = stringResource(R.string.navigation_title_profile))
                                Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding() + 20.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}