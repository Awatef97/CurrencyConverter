package com.example.currencyconverter.features.history.domain.repository

import com.example.currencyconverter.core.domain.DataError
import com.example.currencyconverter.core.domain.Result
import com.example.currencyconverter.features.history.domain.entity.param.HistoricalDataParam
import com.example.currencyconverter.features.history.domain.entity.HistoricalResponseEntity
import kotlinx.coroutines.flow.Flow

interface HistoricalRepository {

     fun getHistoricalData(historicalDataParam: HistoricalDataParam):
             Flow<Result<HistoricalResponseEntity,DataError.Network>>
}