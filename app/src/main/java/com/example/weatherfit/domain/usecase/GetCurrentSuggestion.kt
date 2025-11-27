package com.example.weatherfit.domain.usecase

import com.example.weatherfit.domain.model.FitSuggestion
import com.example.weatherfit.domain.repository.CurrentSuggestionRepository

class GetCurrentSuggestion(private val currentSuggestionRepository: CurrentSuggestionRepository) {
    fun execute() : FitSuggestion? {
        return currentSuggestionRepository.getSuggestion()
    }
}