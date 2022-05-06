package com.masorone.cryptocompare.di

import android.content.Context
import com.masorone.cryptocompare.data.database.CoinDatabase
import com.masorone.cryptocompare.data.repository.CoinRepositoryImpl
import com.masorone.cryptocompare.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun provideCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        fun provideCoinDatabase(context: Context): CoinDatabase {
            return CoinDatabase.getInstance(context)
        }
    }
}