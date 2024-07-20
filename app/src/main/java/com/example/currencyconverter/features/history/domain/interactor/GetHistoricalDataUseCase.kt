package com.example.currencyconverter.features.history.domain.interactor

import com.example.currencyconverter.core.domain.Result
import com.example.currencyconverter.features.history.domain.entity.param.HistoricalDataParam
import com.example.currencyconverter.features.history.domain.repository.HistoricalRepository
import com.example.currencyconverter.features.history.presentation.uimodel.HistoricalDataByDateUIModel
import com.example.currencyconverter.features.history.presentation.uimodel.UIModel
import com.example.currencyconverter.features.history.presentation.uimodel.toHistoricalDataUIModel
import com.example.currencyconverter.features.home.presentation.mapper.handleErrors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
Return Historical data grouped by date
 **/
class GetHistoricalDataUseCase @Inject constructor(
    private val historicalRepository: HistoricalRepository
) {
     operator fun invoke(historicalDataParam: HistoricalDataParam):
             Flow<UIModel> {
         return historicalRepository.getHistoricalData(historicalDataParam = historicalDataParam)
             .map { result ->
                 when(result) {
                     is Result.Success -> {
                         if (result.data.historicalDataEntities != null) {
                             UIModel(historicalDataByDateUIModel = result.data.historicalDataEntities.groupBy { it.date }
                                 .map {
                                     HistoricalDataByDateUIModel(date = it.key, data = it.value.map { it.toHistoricalDataUIModel() })
                                 })
                         }
                         else {
                             UIModel(
                                 errorMsg = result.data.error?.info ?: ""
                             )
                         }
                     }
                     is Result.Error ->{
                         UIModel(
                             errorMsg = result.error.handleErrors()
                         )
                     }
                 }
             }
     }

}