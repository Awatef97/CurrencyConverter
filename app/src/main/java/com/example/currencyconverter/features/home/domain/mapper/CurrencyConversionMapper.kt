package com.example.currencyconverter.features.home.domain.mapper

import com.example.currencyconverter.core.data.ErrorResponse
import com.example.currencyconverter.core.domain.ErrorEntity
import com.example.currencyconverter.features.home.data.model.CurrencyConversionResponse
import com.example.currencyconverter.features.home.domain.entity.CurrencyConversionEntity

fun CurrencyConversionResponse.toEntity() = CurrencyConversionEntity(
    result = this.result,
    success = this.success,
    error = this.error?.toEntity()
)

fun ErrorResponse.toEntity() = ErrorEntity(
    info = info,
    code = code
)