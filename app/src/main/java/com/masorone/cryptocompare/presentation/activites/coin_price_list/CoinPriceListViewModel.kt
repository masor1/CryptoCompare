package com.masorone.cryptocompare.presentation.activites.coin_price_list

import androidx.lifecycle.ViewModel
import com.masorone.cryptocompare.domain.GetCoinInfoListUseCase
import com.masorone.cryptocompare.domain.LoadDataUseCase
import javax.inject.Inject

class CoinPriceListViewModel @Inject constructor(
    getCoinInfoListUseCase: GetCoinInfoListUseCase,
    loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    init {
        loadDataUseCase.invoke()
    }

    val coinInfoList = getCoinInfoListUseCase.invoke()
}