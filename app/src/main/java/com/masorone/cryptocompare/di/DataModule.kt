package com.masorone.cryptocompare.di

import android.content.Context
import com.masorone.cryptocompare.data.database.CoinDatabase
import com.masorone.cryptocompare.data.database.CoinInfoDao
import com.masorone.cryptocompare.data.network.ApiFactory
import com.masorone.cryptocompare.data.network.ApiService
import com.masorone.cryptocompare.data.repository.CoinRepositoryImpl
import com.masorone.cryptocompare.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun provideCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideCoinInfoDao(context: Context): CoinInfoDao {
            return CoinDatabase.getInstance(context).coinPriceInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}