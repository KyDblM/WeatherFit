package com.example.weatherfit.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherFitTheme (viewModel.isDarkTheme.value) {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        CustomNavigationBar(
                            navController = navController,
                            navigationItems = viewModel.navigationItems,
                            currentScreen = viewModel.currentScreen,
                            navigationBarPadding = WindowInsets
                                .navigationBars
                                .only(WindowInsetsSides.Bottom)
                                .asPaddingValues()
                                .calculateBottomPadding()
                        )
                    }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = viewModel.currentScreen.value
                    ) {
                        composable(NavigationRoutes.Registration.route) {
                            RegistrationScreen(
                                QuestionsRepository().registrationQuestions,
                                onFinished = { answers ->
                                    viewModel.saveSettings(answers)
                                    viewModel.updateAppTheme(viewModel.getAppTheme())

                                    navController.navigate("home_screen")
                                }
                            )
                        }

                        composable(NavigationRoutes.Home.route) {
                            HomeScreen(paddingValues)
                        }

                        composable(NavigationRoutes.History.route) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(MaterialTheme.colorScheme.surface),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Top,
                            ) {
                                Spacer(modifier = Modifier.height(paddingValues.calculateTopPadding() + 20.dp))
                                Text(text = stringResource(R.string.navigation_title_history))
                                Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding() + 20.dp))
                            }
                        }

                        composable(NavigationRoutes.Profile.route) {
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