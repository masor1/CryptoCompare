package com.masorone.cryptocompare.di

import android.content.Context
import com.masorone.cryptocompare.data.database.CoinDatabase
import com.masorone.cryptocompare.data.mapper.CoinMapper
import com.masorone.cryptocompare.data.repository.CoinRepositoryImpl
import com.masorone.cryptocompare.domain.CoinRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideCoinDatabase(context: Context): CoinDatabase {
        return CoinDatabase.getInstance(context)
    }

    @Provides
    fun provideCoinRepository(
        context: Context,
        database: CoinDatabase,
        mapper: CoinMapper
    ): CoinRepository = CoinRepositoryImpl(
        context,
        database,
        mapper
    )
}