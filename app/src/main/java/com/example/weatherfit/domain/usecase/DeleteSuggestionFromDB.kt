package com.example.weatherfit.domain.usecase

import com.example.weatherfit.domain.model.FitSuggestion
import com.example.weatherfit.domain.repository.SuggestionsDbRepository

class DeleteSuggestionFromDB(private val suggestionsDbRepository: SuggestionsDbRepository) {
    suspend fun execute(suggestion: FitSuggestion) {
        suggestionsDbRepository.deleteSuggestion(suggestion)
    }
}