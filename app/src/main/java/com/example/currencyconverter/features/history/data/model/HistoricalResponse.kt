package com.example.currencyconverter.features.history.data.model

import com.example.currencyconverter.core.data.ErrorResponse

data class HistoricalResponse(
    val success: Boolean,
    val rates: Map<String, Map<String, Double>>,
    val error: ErrorResponse? = null
)

data class HistoricalData(
    val date: String,
    val currency: String,
    val rate: Double
)

fun HistoricalResponse.toHistoricalDataList(): List<HistoricalData> {
    val historicalDataList = mutableListOf<HistoricalData>()
    for ((date, rateMap) in rates) {
        for ((currency, rate) in rateMap) {
            historicalDataList.add(HistoricalData(date, currency, rate))
        }
    }
    return historicalDataList
}
