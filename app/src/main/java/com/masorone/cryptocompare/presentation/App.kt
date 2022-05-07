package com.masorone.cryptocompare.presentation

import android.app.Application
import androidx.work.Configuration
import com.masorone.cryptocompare.data.worker.CoinWorkerFactory
import com.masorone.cryptocompare.di.DaggerAppComponent
import javax.inject.Inject

class App : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: CoinWorkerFactory

    val appComponent by lazy {
        DaggerAppComponent.factory()
            .create(this)
    }

    override fun onCreate() {
        appComponent.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}