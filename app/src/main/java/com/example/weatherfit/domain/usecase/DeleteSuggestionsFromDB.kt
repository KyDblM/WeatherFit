package com.example.weatherfit.domain.usecase

import com.example.weatherfit.domain.model.FitSuggestion
import com.example.weatherfit.domain.repository.SuggestionsDbRepository

class DeleteSuggestionsFromDB(private val suggestionsDbRepository: SuggestionsDbRepository) {
    suspend fun execute(suggestions: List<FitSuggestion>) {
        suggestionsDbRepository.deleteSuggestions(suggestions)
    }
}