package com.example.currencyconverter.features.history.data.source.remote

import com.example.currencyconverter.BuildConfig
import com.example.currencyconverter.features.history.data.model.HistoricalResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HistoricalDataService {
    @GET("timeseries")
    suspend fun getHistoricalData(
        @Query("access_key") access_key: String = BuildConfig.API_KEY,
        @Query("start_date") start_date: String,
        @Query("end_date") end_date: String,
        @Query("base") base: String,
        @Query("symbols") symbols: String
    ): HistoricalResponse
}