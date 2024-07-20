package com.example.currencyconverter.features.history.data.repository

import com.example.currencyconverter.core.domain.DataError
import com.example.currencyconverter.core.domain.Result
import com.example.currencyconverter.features.history.data.model.HistoricalResponse
import com.example.currencyconverter.features.history.data.model.toHistoricalDataList
import com.example.currencyconverter.features.history.data.source.remote.HistoricalDataService
import com.example.currencyconverter.features.history.domain.entity.mapper.toEntity
import com.example.currencyconverter.features.history.domain.entity.param.HistoricalDataParam
import com.example.currencyconverter.features.history.domain.entity.HistoricalResponseEntity
import com.example.currencyconverter.features.history.domain.repository.HistoricalRepository
import com.example.currencyconverter.features.home.domain.mapper.toEntity
import com.google.gson.JsonParseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HistoricalRepositoryImp @Inject constructor(
    private val historicalDataService: HistoricalDataService
): HistoricalRepository {
    override fun getHistoricalData(historicalDataParam: HistoricalDataParam): Flow<Result<HistoricalResponseEntity, DataError.Network>> {
        return flow<Result<HistoricalResponseEntity, DataError.Network>>
        {
            try {
                var result = historicalDataService.getHistoricalData(
                    start_date = historicalDataParam.start_date,
                    end_date = historicalDataParam.end_date,
                    base = historicalDataParam.base,
                    symbols = historicalDataParam.symbols
                )
                /**if you want to mock fake data**/
//                result =  HistoricalResponse(
//                    success = true,
//                    rates = mapOf(
//                        "2023-07-17" to mapOf("USD" to 1.1234, "EUR" to 0.9876),
//                        "2023-07-18" to mapOf("USD" to 1.2345, "EUR" to 1.0011),
//                        "2023-07-19" to mapOf("USD" to 1.3456, "EUR" to 1.0234),
//                        "2023-07-20" to mapOf("USD" to 1.4567, "EUR" to 1.0456)
//                    ))
                if (result.success)
                    emit(Result.Success(HistoricalResponseEntity(historicalDataEntities = result.toHistoricalDataList().map { it.toEntity() })))
                else
                    emit(Result.Success(HistoricalResponseEntity(error = result.error?.toEntity())))
            }catch (e: IOException) {
                emit(Result.Error(DataError.Network.NO_INTERNET))
            } catch (e: HttpException) {
                when (e.code()) {
                    408 -> emit(Result.Error(DataError.Network.REQUEST_TIMEOUT))
                    413 -> emit(Result.Error(DataError.Network.TOO_LARGE_PAYLOAD))
                    else -> emit(Result.Error(DataError.Network.UNKNOWN))
                }
            } catch (e: JsonParseException) {
                emit(Result.Error(DataError.Network.SERIALIZATION))
            }
        }.flowOn(Dispatchers.IO)
    }
}