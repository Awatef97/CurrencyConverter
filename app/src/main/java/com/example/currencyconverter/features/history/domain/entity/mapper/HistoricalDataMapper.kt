package com.example.currencyconverter.features.history.domain.entity.mapper

import com.example.currencyconverter.features.history.data.model.HistoricalData
import com.example.currencyconverter.features.history.domain.entity.HistoricalDataEntity

fun HistoricalData.toEntity() = HistoricalDataEntity(
    date = date,
    currency = "Currency: $currency",
    rate = "Rate: $rate"
)