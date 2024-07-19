package com.example.currencyconverter.features.home.domain.mapper

import com.example.currencyconverter.features.home.data.mode.CurrencyConversionResponse
import com.example.currencyconverter.features.home.data.mode.ErrorResponse
import com.example.currencyconverter.features.home.domain.entity.CurrencyConversionEntity
import com.example.currencyconverter.features.home.domain.entity.ErrorEntity

fun CurrencyConversionResponse.toEntity() = CurrencyConversionEntity(
    result = this.result,
    success = this.success,
    error = this.error?.toEntity()
)

fun ErrorResponse.toEntity() = ErrorEntity(
    info = info,
    code = code
)