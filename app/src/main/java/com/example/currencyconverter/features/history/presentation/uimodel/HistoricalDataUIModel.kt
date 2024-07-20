package com.example.currencyconverter.features.history.presentation.uimodel

import com.example.currencyconverter.features.history.domain.entity.HistoricalDataEntity

data class UIModel(
    val historicalDataByDateUIModel: List<HistoricalDataByDateUIModel>? = null,
    val errorMsg: String? = null
)
data class HistoricalDataByDateUIModel(
    val date: String? = null,
    val data: List<HistoricalDataUIModel>? = null
)
data class HistoricalDataUIModel(
    val date: String? = null,
    val currency: String,
    val rate: String
)

fun HistoricalDataEntity.toHistoricalDataUIModel() = HistoricalDataUIModel(
    currency = currency,
    rate = rate,
    date = date
)

