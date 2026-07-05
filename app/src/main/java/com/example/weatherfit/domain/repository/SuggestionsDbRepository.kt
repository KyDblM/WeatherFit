package com.example.weatherfit.domain.repository

import com.example.weatherfit.domain.model.FitSuggestion

interface SuggestionsDbRepository {
    suspend fun saveSuggestion(suggestion: FitSuggestion)

    suspend fun deleteSuggestion(suggestion: FitSuggestion)

    suspend fun deleteSuggestions(suggestions: List<FitSuggestion>)

    suspend fun getSuggestions(): List<FitSuggestion>

    suspend fun updateSuggestion(suggestion: FitSuggestion)
}