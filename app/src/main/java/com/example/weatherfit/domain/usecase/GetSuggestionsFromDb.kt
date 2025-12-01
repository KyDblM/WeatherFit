package com.example.weatherfit.domain.usecase

import com.example.weatherfit.domain.model.FitSuggestion
import com.example.weatherfit.domain.repository.SuggestionsDbRepository

class GetSuggestionsFromDb(private val suggestionsDbRepository: SuggestionsDbRepository) {
    suspend fun execute(): List<FitSuggestion> {
        return suggestionsDbRepository.getSuggestions()
    }
}