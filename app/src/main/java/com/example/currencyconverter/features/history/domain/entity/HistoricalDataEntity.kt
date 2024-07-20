package com.example.currencyconverter.features.history.domain.entity

import com.example.currencyconverter.core.domain.ErrorEntity


data class HistoricalResponseEntity(
    val historicalDataEntities: List<HistoricalDataEntity>? = null,
    val error: ErrorEntity? = null
)

data class HistoricalDataEntity(
    val date: String,
    val currency: String,
    val rate: String
)
