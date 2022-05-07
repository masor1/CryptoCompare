package com.masorone.cryptocompare.di

import android.content.Context
import com.masorone.cryptocompare.presentation.App
import com.masorone.cryptocompare.presentation.activites.coin_detail.CoinDetailActivity
import com.masorone.cryptocompare.presentation.activites.coin_price_list.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(coinPriceListActivity: CoinPriceListActivity)

    fun inject(coinDetailActivity: CoinDetailActivity)

    fun inject(app: App)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }
}