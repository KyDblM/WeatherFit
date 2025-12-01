package com.example.weatherfit.data.repository

import com.example.weatherfit.data.storage.dao.SuggestionDao
import com.example.weatherfit.domain.model.FitSuggestion
import com.example.weatherfit.domain.repository.SuggestionsDbRepository
import jakarta.inject.Inject

class SuggestionsDbRepositoryImpl @Inject constructor(
    private val dao: SuggestionDao
) : SuggestionsDbRepository {
    override suspend fun saveSuggestion(suggestion: FitSuggestion) {
        dao.saveSuggestion(suggestion)
    }

    override suspend fun deleteSuggestion(suggestion: FitSuggestion) {
        dao.deleteSuggestion(suggestion)
    }

    override suspend fun deleteSuggestions(suggestions: List<FitSuggestion>) {
        dao.deleteSuggestions(suggestions)
    }

    override suspend fun getSuggestions(): List<FitSuggestion> {
        return dao.getSuggestions()
    }
}