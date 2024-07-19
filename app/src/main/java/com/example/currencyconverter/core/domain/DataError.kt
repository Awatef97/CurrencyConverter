package com.example.currencyconverter.core.domain

sealed interface DataError: Error {
    enum class Network: DataError{
        NO_INTERNET,
        REQUEST_TIMEOUT,
        TOO_LARGE_PAYLOAD,
        SERIALIZATION,
        EXCEED_API_CALL_MAX_NUMBER,
        UNKNOWN
    }
    enum class SpecificError(val message: String): DataError{
        BAD_REQUEST(message = ""),
//        NO_API_KEY,
//        SUBSCRIPTION_PLAN_NOT_SUPPORTED_THIS_API,
//        REQUEST_RESOURCE_NOT_EXIST,
//        REQUEST_API_ENDPOINT_NOT_EXIST,
//        INVALID_BASE_CURRENCY,
//        INVALID_SYMBOLS,
//        INVALID_DATA,
//        INVALID_AMOUNT,
//        INVALID_TIME_FRAME,
    }
}