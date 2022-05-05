package com.masorone.cryptocompare.presentation

import android.app.Application
import com.masorone.cryptocompare.di.DaggerAppComponent

class App : Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory()
            .create(this)
    }
}