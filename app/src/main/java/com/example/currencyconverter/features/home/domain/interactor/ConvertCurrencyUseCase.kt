package com.example.currencyconverter.features.home.domain.interactor

import com.example.currencyconverter.features.home.domain.entity.CurrencyConversionParam
import com.example.currencyconverter.features.home.domain.repository.CurrencyConversionRepository
import javax.inject.Inject

class ConvertCurrencyUseCase @Inject constructor(
    private val conversionRepository: CurrencyConversionRepository
) {
    suspend operator fun invoke(conversionParam: CurrencyConversionParam) =
        conversionRepository.convertCurrency(conversionParam)


}