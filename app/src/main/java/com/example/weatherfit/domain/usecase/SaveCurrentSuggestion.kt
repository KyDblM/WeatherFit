package com.example.weatherfit.domain.usecase

import com.example.weatherfit.domain.model.FitSuggestion
import com.example.weatherfit.domain.repository.CurrentSuggestionRepository

class SaveCurrentSuggestion(private val currentSuggestionRepository: CurrentSuggestionRepository) {
    fun execute(suggestion: FitSuggestion) {
        currentSuggestionRepository.save(suggestion)
    }
}