package com.example.currencyconverter.features.home.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.core.domain.Result
import com.example.currencyconverter.features.home.domain.entity.CurrencyConversionParam
import com.example.currencyconverter.features.home.domain.interactor.ConvertCurrencyUseCase
import com.example.currencyconverter.features.home.presentation.mapper.handleErrors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyConversionViewModel @Inject constructor(
    private val currencyUseCase: ConvertCurrencyUseCase
): ViewModel(){

    val currencyList = MutableLiveData<List<String>>()
    val fromCurrencyPosition = MutableLiveData<Int>()
    val toCurrencyPosition = MutableLiveData<Int>()
    val amount = MutableLiveData<String>()
    val convertedValue = MutableLiveData<String>()
    private val conversionParam: CurrencyConversionParam =
        CurrencyConversionParam(
            from = currencyList.value?.get(fromCurrencyPosition.value ?: 0) ?: "USD",
            to = currencyList.value?.get(toCurrencyPosition.value ?: 0) ?: "EUR",
            amount = amount.value?.toLongOrNull() ?: 1
        )

    init {
        currencyList.value = getAllCurrencies()
        fromCurrencyPosition.value = 0
        toCurrencyPosition.value = 1
        amount.value = "1"
        amount.observeForever { newAmount ->
            newAmount?.let {
                convertCurrency(
                    conversionParam = conversionParam.copy(amount = it.toLongOrNull() ?: 1)
                )
            }
        }
    }
    fun swapCurrencies() {
        val fromPosition = fromCurrencyPosition.value ?: 0
        fromCurrencyPosition.value = toCurrencyPosition.value
        toCurrencyPosition.value = fromPosition
        convertCurrency(conversionParam = conversionParam)
    }

    private fun convertCurrency(conversionParam: CurrencyConversionParam) {
        viewModelScope.launch {
            currencyUseCase.invoke(conversionParam = conversionParam)
                .collectLatest {
                    when (it) {
                        is Result.Error -> {
                            convertedValue.value = it.error.handleErrors()
                        }
                        is Result.Success -> {
                            if (it.date.success == true)
                                convertedValue.value = it.date.result.toString()
                            else {
                                convertedValue.value = it.date.error?.info.toString()

                            }
                        }
                    }
                }
        }
    }
    private fun getAllCurrencies(): List<String> {
        return listOf(
            "AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN",
            "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BRL",
            "BSD", "BTN", "BWP", "BYN", "BZD", "CAD", "CDF", "CHF", "CLP", "CNY",
            "COP", "CRC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "EGP",
            "ERN", "ETB", "EUR", "FJD", "FKP", "FOK", "GBP", "GEL", "GGP", "GHS",
            "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", "HUF",
            "IDR", "ILS", "IMP", "INR", "IQD", "IRR", "ISK", "JMD", "JOD", "JPY",
            "KES", "KGS", "KHR", "KID", "KMF", "KRW", "KWD", "KYD", "KZT", "LAK",
            "LBP", "LKR", "LRD", "LSL", "LYD", "MAD", "MDL", "MGA", "MKD", "MMK",
            "MNT", "MOP", "MRU", "MUR", "MVR", "MWK", "MXN", "MYR", "MZN", "NAD",
            "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", "PGK", "PHP",
            "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RWF", "SAR", "SBD",
            "SCR", "SDG", "SEK", "SGD", "SHP", "SLE", "SLL", "SOS", "SRD", "SSP",
            "STN", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD",
            "TVD", "TWD", "TZS", "UAH", "UGX", "USD", "UYU", "UZS", "VED", "VES",
            "VND", "VUV", "WST", "XAF", "XCD", "XDR", "XOF", "XPF", "YER", "ZAR",
            "ZMW", "ZWL"
        )
    }
}
