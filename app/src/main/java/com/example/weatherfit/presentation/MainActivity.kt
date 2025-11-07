package com.example.weatherfit.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.location.LocationManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.LaunchedEffect
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
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.time.Duration

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    private val locationPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val fineGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false

            if (fineGranted && isGpsAvailable()) {
                getGpsLocation()
            }
            else {
                if (isInternetAvailable()) {
                    getIpLocation()
                }
            }
        }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        window.isNavigationBarContrastEnforced = false
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.auto(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            )
        )

        locationPermissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))

        setContent {
            LaunchedEffect(viewModel.location.value) {
                viewModel.location.value.let {
                    viewModel.getWeather(viewModel.location)
                }
            }

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

                                    navController.navigate(NavigationRoutes.Home.route)
                                }
                            )
                        }

                        composable(route = NavigationRoutes.Home.route) {
                            HomeScreen(
                                paddingValues = paddingValues,
                                weatherData = viewModel.weather,
                                onMannequinClick = {
                                    TODO("Navigation to the survey or suggestion editing windows")
                                }
                            )
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

    fun isInternetAvailable(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    fun isGpsAvailable(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    @SuppressLint("MissingPermission")
    private fun getGpsLocation() {
        val fusedClient = LocationServices.getFusedLocationProviderClient(this)

        fusedClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    viewModel.location.value = location.latitude.toString() + "," + location.longitude.toString()
                } else {
                    val request = LocationRequest.Builder(
                        Priority.PRIORITY_HIGH_ACCURACY,
                        1000L
                    )
                        .setMaxUpdates(1)
                        .build()

                    val callback = object : LocationCallback() {
                        override fun onLocationResult(result: LocationResult) {
                            val location = result.lastLocation
                            if (location != null) {
                                viewModel.location.value = location.latitude.toString() + "," + location.longitude.toString()
                            }
                            fusedClient.removeLocationUpdates(this)
                        }
                    }

                    fusedClient.requestLocationUpdates(
                        request,
                        callback,
                        Looper.getMainLooper()
                    )
                }
            }
    }

    private fun getIpLocation() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getLocation()
        }
    }
}