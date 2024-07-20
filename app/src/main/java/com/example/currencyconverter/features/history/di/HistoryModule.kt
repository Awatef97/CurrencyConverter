package com.example.currencyconverter.features.history.di

import com.example.currencyconverter.features.history.data.repository.HistoricalRepositoryImp
import com.example.currencyconverter.features.history.data.source.remote.HistoricalDataService
import com.example.currencyconverter.features.history.domain.repository.HistoricalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
class HistoryModule {
    @ViewModelScoped
    @Provides
    fun provideHistoricalDataService(retrofit: Retrofit): HistoricalDataService {
        return retrofit.create(HistoricalDataService::class.java)
    }

    @ViewModelScoped
    @Provides
    fun provideHistoricalRepositoryImpl(
        historicalDataService: HistoricalDataService
    ): HistoricalRepository {
        return HistoricalRepositoryImp(historicalDataService)
    }
}