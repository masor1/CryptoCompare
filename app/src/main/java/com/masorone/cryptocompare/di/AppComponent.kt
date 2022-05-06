package com.masorone.cryptocompare.di

import android.content.Context
import com.masorone.cryptocompare.presentation.activites.coin_detail.CoinDetailActivity
import com.masorone.cryptocompare.presentation.activites.coin_price_list.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(coinPriceListActivity: CoinPriceListActivity)
    fun inject(coinDetailActivity: CoinDetailActivity)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }
}