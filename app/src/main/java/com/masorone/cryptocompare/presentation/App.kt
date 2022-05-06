package com.masorone.cryptocompare.presentation

import android.app.Application
import androidx.work.Configuration
import com.masorone.cryptocompare.data.database.CoinDatabase
import com.masorone.cryptocompare.data.mapper.CoinMapper
import com.masorone.cryptocompare.data.network.ApiFactory
import com.masorone.cryptocompare.data.network.ApiService
import com.masorone.cryptocompare.data.worker.RefreshDataWorkerFactory
import com.masorone.cryptocompare.di.DaggerAppComponent

class App : Application(), Configuration.Provider {

    val appComponent by lazy {
        DaggerAppComponent.factory()
            .create(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(
                RefreshDataWorkerFactory(
                    CoinDatabase.getInstance(this).coinPriceInfoDao(),
                    ApiFactory.apiService,
                    CoinMapper()
                )
            )
            .build()
    }
}