package com.masorone.cryptocompare.di

import com.masorone.cryptocompare.domain.CoinRepository
import com.masorone.cryptocompare.domain.GetCoinInfoListUseCase
import com.masorone.cryptocompare.domain.GetCoinInfoUseCase
import com.masorone.cryptocompare.domain.LoadDataUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetCoinInfoListUseCase(repository: CoinRepository): GetCoinInfoListUseCase =
        GetCoinInfoListUseCase(repository)

    @Provides
    fun provideGetCoinInfoUseCase(repository: CoinRepository): GetCoinInfoUseCase =
        GetCoinInfoUseCase(repository)

    @Provides
    fun provideLoadDataUseCase(repository: CoinRepository): LoadDataUseCase =
        LoadDataUseCase(repository)
}