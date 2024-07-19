package com.example.currencyconverter.features.home.data.repository

import com.example.currencyconverter.core.domain.DataError
import com.example.currencyconverter.core.domain.Result
import com.example.currencyconverter.features.home.data.source.remote.CurrencyConversionService
import com.example.currencyconverter.features.home.domain.entity.CurrencyConversionEntity
import com.example.currencyconverter.features.home.domain.entity.CurrencyConversionParam
import com.example.currencyconverter.features.home.domain.mapper.toEntity
import com.example.currencyconverter.features.home.domain.repository.CurrencyConversionRepository
import com.google.gson.JsonParseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CurrencyConversionRepositoryImp @Inject constructor(
    private val currencyConversionService: CurrencyConversionService,
) : CurrencyConversionRepository {
    override suspend fun convertCurrency(currencyConversionParam: CurrencyConversionParam): Flow<Result<CurrencyConversionEntity, DataError.Network>> {
        return flow<Result<CurrencyConversionEntity, DataError.Network>> {
            try {

                val result = currencyConversionService
                    .convertCurrency(
                        from = currencyConversionParam.from,
                        to = currencyConversionParam.to,
                        amount = currencyConversionParam.amount
                    )
                emit(Result.Success(result.toEntity()))

            } catch (e: IOException) {
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