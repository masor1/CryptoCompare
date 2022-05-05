package com.masorone.cryptocompare.di

import androidx.lifecycle.ViewModel
import com.masorone.cryptocompare.presentation.activites.coin_detail.CoinDetailViewModel
import com.masorone.cryptocompare.presentation.activites.coin_price_list.CoinPriceListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(CoinPriceListViewModel::class)
    @Binds
    fun bindCoinPriceListViewModel(impl: CoinPriceListViewModel): ViewModel

    @IntoMap
    @ViewModelKey(CoinDetailViewModel::class)
    @Binds
    fun bindCoinDetailViewModel(impl: CoinDetailViewModel): ViewModel
}