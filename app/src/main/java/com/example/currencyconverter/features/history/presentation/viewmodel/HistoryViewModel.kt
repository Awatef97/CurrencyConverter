package com.example.currencyconverter.features.history.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.features.history.domain.entity.param.HistoricalDataParam
import com.example.currencyconverter.features.history.domain.interactor.GetHistoricalDataUseCase
import com.example.currencyconverter.features.history.presentation.uimodel.HistoricalDataByDateUIModel
import com.example.currencyconverter.features.history.presentation.view.HistoricalDataAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getHistoricalDataUseCase: GetHistoricalDataUseCase
) : ViewModel(){
    val errorMsg = MutableLiveData<String>()
    val historicalData = MutableLiveData<List<HistoricalDataByDateUIModel>>()

    fun fetchHistoricalData(historicalDataParam: HistoricalDataParam) {
        viewModelScope.launch {
            getHistoricalDataUseCase.invoke(historicalDataParam)
                .collectLatest {
                    errorMsg.value = it.errorMsg ?: ""
                    historicalData.value = it.historicalDataByDateUIModel ?: emptyList()
                }
        }
    }
}