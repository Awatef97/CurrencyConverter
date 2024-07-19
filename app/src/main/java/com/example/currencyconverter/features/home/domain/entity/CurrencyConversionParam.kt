package com.example.currencyconverter.features.home.domain.entity

data class CurrencyConversionParam(
    val from: String,
    val to: String,
    val amount: Long
)
