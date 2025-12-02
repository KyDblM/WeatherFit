package com.example.weatherfit.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherfit.data.storage.dao.SuggestionDao
import com.example.weatherfit.data.util.Converters
import com.example.weatherfit.domain.model.FitSuggestion

@Database(
    entities = [FitSuggestion::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class Database: RoomDatabase() {
    abstract fun suggestionDao(): SuggestionDao

    companion object {
        const val DATABASE_NAME = "database.db"
    }
}