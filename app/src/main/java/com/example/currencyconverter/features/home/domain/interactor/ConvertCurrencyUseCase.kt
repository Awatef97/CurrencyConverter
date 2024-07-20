package com.example.currencyconverter.features.home.domain.interactor

import com.example.currencyconverter.features.home.domain.entity.CurrencyConversionParam
import com.example.currencyconverter.features.home.domain.repository.CurrencyConversionRepository
import javax.inject.Inject
/**
 hint: @{ConvertCurrencyUseCase} is optional in this sample because
 a UseCase layer, it would handle the business logic.
 In this case, we may not need complex transformations, so we can skip it.
 **/
class ConvertCurrencyUseCase @Inject constructor(
    private val conversionRepository: CurrencyConversionRepository
) {
    suspend operator fun invoke(conversionParam: CurrencyConversionParam) =
        conversionRepository.convertCurrency(conversionParam)


}