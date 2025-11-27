package com.example.weatherfit.domain.repository

import com.example.weatherfit.domain.model.FitSuggestion

interface CurrentSuggestionRepository {
    fun save(suggestion: FitSuggestion)

    fun getSuggestion() : FitSuggestion?
}