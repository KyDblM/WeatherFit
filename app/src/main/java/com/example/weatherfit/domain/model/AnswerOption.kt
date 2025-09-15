package com.example.weatherfit.domain.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.weatherfit.R

enum class AppTheme(@StringRes val label: Int) {
    LIGHT(R.string.first_initial_setup_option_light),
    DARK(R.string.first_initial_setup_option_dark)
}

enum class MannequinGender(@StringRes val label: Int) {
    MALE(R.string.second_initial_setup_option_male),
    FEMALE(R.string.second_initial_setup_option_female)
}

enum class LocationType(@StringRes val label: Int) {
    INDOOR(R.string.location_type_option_indoor),
    OUTDOOR(R.string.location_type_option_outdoor)
}

enum class ActivityMode(@StringRes val label: Int) {
    REGULAR(R.string.activity_mode_option_regular),
    SPORT(R.string.activity_mode_option_sport)
}

sealed class AnswerOption {
    data class Theme(val value: AppTheme, @StringRes val label: Int = value.label) : AnswerOption()
    data class Gender(val value: MannequinGender, @StringRes val label: Int = value.label) : AnswerOption()
    data class Hours(val value: Int) : AnswerOption()
    data class Location(val value: LocationType, @StringRes val label: Int = value.label) : AnswerOption()
    data class Activity(val value: ActivityMode, @StringRes val label: Int = value.label) : AnswerOption()

    @Composable
    fun displayText(): String = when(this) {
        is Theme -> stringResource(label)
        is Gender -> stringResource(label)
        is Hours -> value.toString()
        is Location -> stringResource(label)
        is Activity -> stringResource(label)
    }
}