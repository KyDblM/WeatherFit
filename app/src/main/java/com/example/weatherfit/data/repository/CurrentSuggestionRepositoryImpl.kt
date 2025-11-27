package com.example.weatherfit.data.repository

import android.content.Context
import androidx.core.content.edit
import com.example.weatherfit.domain.model.FitSuggestion
import com.example.weatherfit.domain.repository.CurrentSuggestionRepository
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private const val SUGGESTION_KEY = "suggestion"

class CurrentSuggestionRepositoryImpl(val context: Context): CurrentSuggestionRepository {
    private val sharedPreferences = context.getSharedPreferences("current_suggestion", Context.MODE_PRIVATE)

    override fun save(suggestion: FitSuggestion) {
        //val suggestionJson = Json.encodeToString(suggestion)
        val suggestionJson = Json.encodeToString(suggestion)

        sharedPreferences.edit { putString(SUGGESTION_KEY, suggestionJson) }
    }

    override fun getSuggestion(): FitSuggestion? {
        val suggestionJson = sharedPreferences.getString(SUGGESTION_KEY, null)

        return if (suggestionJson != null) {
            Json.decodeFromString<FitSuggestion>(suggestionJson)
        }
        else {
            null
        }
    }
}