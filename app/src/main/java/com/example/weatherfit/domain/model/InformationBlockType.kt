package com.example.weatherfit.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class InformationBlockType {
    data class OnlyImage(@DrawableRes val image: Int) : InformationBlockType()
    data class TextWithTitleAndImage(@DrawableRes val image: Int, @StringRes val title: Int, @StringRes val text: Int) : InformationBlockType()
}