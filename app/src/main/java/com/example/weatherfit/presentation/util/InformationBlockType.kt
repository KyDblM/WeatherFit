package com.example.weatherfit.presentation.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class InformationBlockType {
    data class OnlyImage(@DrawableRes val image: Int) : InformationBlockType()
    data class OnlyIcon (@DrawableRes val icon: Int) : InformationBlockType()
    data class OnlyText(val text: String): InformationBlockType()
    data class TextWithTitleAndImage(@DrawableRes val image: Int, @StringRes val title: Int, val text: String) : InformationBlockType()
}