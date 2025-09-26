package com.example.weatherfit.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherfit.domain.repository.QuestionsRepository
import com.example.weatherfit.presentation.screens.RegistrationScreen
import com.example.weatherfit.presentation.theme.WeatherFitTheme
import dagger.hilt.EntryPoint

lateinit var viewModel: MainViewModel

@EntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherFitTheme (viewModel.isDarkTheme.value) {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = if (viewModel.checkSettingsExist()) "home_screen" else "registration_screen"
                ) {
                    composable("registration_screen") {
                        RegistrationScreen(
                            QuestionsRepository().registrationQuestions,
                            onFinished = { answers ->
                                viewModel.saveSettings(answers)
                                viewModel.updateAppTheme(viewModel.getAppTheme())

                                navController.navigate("home_screen")
                            }
                        )
                    }
                }
            }
        }
    }
}