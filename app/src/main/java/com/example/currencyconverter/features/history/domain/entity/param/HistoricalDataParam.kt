package com.example.currencyconverter.features.history.domain.entity.param

data class HistoricalDataParam(
    val start_date: String,
    val end_date: String,
    val base: String,
    val symbols: String,
)
