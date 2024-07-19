package com.example.currencyconverter.features.home.presentation.mapper

import com.example.currencyconverter.R
import com.example.currencyconverter.core.application.CurrencyConverterApplication
import com.example.currencyconverter.core.domain.DataError


fun DataError.Network.handleErrors() : String {
    val context = CurrencyConverterApplication.instance
    return when (this) {

        DataError.Network.REQUEST_TIMEOUT -> context.getString(R.string.the_request_timed_out)

        DataError.Network.NO_INTERNET -> context.getString(R.string.no_internet)

        DataError.Network.TOO_LARGE_PAYLOAD ->context.getString( R.string.file_too_large)

        DataError.Network.SERIALIZATION -> context.getString(R.string.error_serialization)

        DataError.Network.UNKNOWN -> context.getString(R.string.unknown_error)

        else -> {
        context.getString(R.string.unknown_error)
        }
    }
}