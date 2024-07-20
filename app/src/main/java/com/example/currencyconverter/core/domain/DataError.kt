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
}