package com.example.currencyconverter.features.home.domain.entity

data class CurrencyConversionEntity(
    val success: Boolean? = null,
   // val historical: String? = null,
   // val date: String? = null,
    val result: Long? = null,
    val error: ErrorEntity? = null
)
//data class QueryEntity(
//    val from: String? = null,
//    val to: String? = null,
//    val amount: Long? = null
//)
//
//data class InfoEntity(
//    val timestamp: Long? = null,
//    val rate: Long? = null
//)
data class ErrorEntity(
    val code: Int? = null,
    val info: String? = null
)
