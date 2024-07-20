package com.example.currencyconverter.features.home.domain.entity

import com.example.currencyconverter.core.domain.ErrorEntity

data class CurrencyConversionEntity(
    val success: Boolean? = null,
    val result: Long? = null,
    val error: ErrorEntity? = null
)
