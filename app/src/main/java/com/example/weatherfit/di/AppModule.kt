package com.example.weatherfit.di

import android.app.Application
import androidx.room.Room
import com.example.weatherfit.data.network.service.IpInfoApi
import com.example.weatherfit.data.network.service.WeatherApi
import com.example.weatherfit.data.storage.dao.SuggestionDao
import com.example.weatherfit.data.storage.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Named("IpInfoRetrofit")
    fun provideIpInfoRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.ipgeolocation.io/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideIpInfoApi(@Named("IpInfoRetrofit") retrofit: Retrofit): IpInfoApi {
        return retrofit.create(IpInfoApi::class.java)
    }

    @Provides
    @Named("WeatherDataRetrofit")
    fun provideWeatherDataRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideWeatherApi(@Named("WeatherDataRetrofit") retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(application: Application): Database {
        return Room.databaseBuilder(
            application,
            Database::class.java,
            Database.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideSuggestionDao(database: Database): SuggestionDao {
        return database.suggestionDao()
    }
}