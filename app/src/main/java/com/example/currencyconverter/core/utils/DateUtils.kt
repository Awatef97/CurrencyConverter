package com.example.currencyconverter.core.utils

import java.text.SimpleDateFormat
import java.util.*

fun getCurrentDate(): String {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(calendar.time)
}

fun getDateFourDaysBefore(): String {
    val calendar = Calendar.getInstance()

    calendar.add(Calendar.DAY_OF_YEAR, -4)

    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(calendar.time)
}