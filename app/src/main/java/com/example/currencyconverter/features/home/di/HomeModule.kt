package com.example.currencyconverter.features.home.di

import com.example.currencyconverter.features.home.data.repository.CurrencyConversionRepositoryImp
import com.example.currencyconverter.features.home.data.source.remote.CurrencyConversionService
import com.example.currencyconverter.features.home.domain.repository.CurrencyConversionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
class HomeModule {
    @ViewModelScoped
    @Provides
    fun provideCurrencyConversionService(retrofit: Retrofit): CurrencyConversionService {
        return retrofit.create(CurrencyConversionService::class.java)
    }

    @ViewModelScoped
    @Provides
    fun provideMainRepositoryImpl(
        currencyConversionService:CurrencyConversionService
    ): CurrencyConversionRepository {
        return CurrencyConversionRepositoryImp(currencyConversionService)
    }
}