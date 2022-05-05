package com.masorone.cryptocompare.presentation.activites.coin_price_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.masorone.cryptocompare.data.repository.CoinRepositoryImpl
import com.masorone.cryptocompare.domain.GetCoinInfoListUseCase
import com.masorone.cryptocompare.domain.LoadDataUseCase
import javax.inject.Inject

class CoinPriceListViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    init {
        loadDataUseCase.invoke()
    }

    val coinInfoList = getCoinInfoListUseCase.invoke()
}