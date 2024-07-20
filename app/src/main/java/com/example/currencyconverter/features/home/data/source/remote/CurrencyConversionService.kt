package com.example.currencyconverter.features.home.data.source.remote

import com.example.currencyconverter.BuildConfig
import com.example.currencyconverter.features.home.data.model.CurrencyConversionResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface CurrencyConversionService {
    @POST("convert")
    suspend fun convertCurrency(
        @Query("access_key") accessKey: String = BuildConfig.API_KEY,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Long
    ): CurrencyConversionResponse

}