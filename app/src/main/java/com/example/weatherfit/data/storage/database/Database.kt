package com.example.weatherfit.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherfit.data.storage.dao.SuggestionDao
import com.example.weatherfit.domain.model.FitSuggestion

@Database(
    entities = [FitSuggestion::class],
    version = 1
)
abstract class Database: RoomDatabase() {
    abstract fun suggestionDao(): SuggestionDao

    companion object {
        const val DATABASE_NAME = "database.db"
    }
}