package com.example.currencyconverter.features.home.domain.repository

import com.example.currencyconverter.core.domain.DataError
import com.example.currencyconverter.core.domain.Result
import com.example.currencyconverter.features.home.domain.entity.CurrencyConversionEntity
import com.example.currencyconverter.features.home.domain.entity.CurrencyConversionParam
import kotlinx.coroutines.flow.Flow

interface CurrencyConversionRepository {

     suspend fun convertCurrency(currencyConversionParam: CurrencyConversionParam):
             Flow<Result<CurrencyConversionEntity, DataError.Network>>
}