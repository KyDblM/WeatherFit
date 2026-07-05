package com.example.weatherfit.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.weatherfit.domain.model.FitSuggestion
import com.example.weatherfit.domain.util.Feedback

@Dao
interface SuggestionDao {
    @Insert
    suspend fun saveSuggestion(suggestion: FitSuggestion)

    @Delete
    suspend fun deleteSuggestion(suggestion: FitSuggestion)

    @Delete
    suspend fun deleteSuggestions(suggestions: List<FitSuggestion>)

    @Query("SELECT * FROM suggestions ORDER BY time DESC")
    suspend fun getSuggestions(): List<FitSuggestion>

    @Update
    suspend fun updateSuggestion(suggestion: FitSuggestion)
}