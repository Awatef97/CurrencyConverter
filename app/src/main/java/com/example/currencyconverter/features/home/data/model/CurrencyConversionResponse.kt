package com.example.currencyconverter.features.home.data.model

import com.example.currencyconverter.core.data.ErrorResponse

data class CurrencyConversionResponse(
    val success: Boolean? = null,
    val historical: String? = null,
    val date: String? = null,
    val result: Long? = null,
    val queryResponse: QueryResponse? = null,
    val infoResponse: InfoResponse? = null,
    val error: ErrorResponse? = null
)
data class QueryResponse(
    val from: String? = null,
    val to: String? = null,
    val amount: Long? = null
)

data class InfoResponse(
    val timestamp: Long? = null,
    val rate: Long? = null
)
