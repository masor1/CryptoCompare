package com.masorone.cryptocompare.presentation.activites.coin_price_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.masorone.cryptocompare.data.repository.CoinRepositoryImpl
import com.masorone.cryptocompare.domain.GetCoinInfoListUseCase
import com.masorone.cryptocompare.domain.LoadDataUseCase

class CoinPriceListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    init {
        loadDataUseCase.invoke()
    }

    val coinInfoList = getCoinInfoListUseCase.invoke()
}