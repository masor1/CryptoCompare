package com.masorone.cryptocompare.presentation.activites.coin_price_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.masorone.cryptocompare.data.repository.CoinRepositoryImpl
import com.masorone.cryptocompare.domain.GetCoinInfoListUseCase
import com.masorone.cryptocompare.domain.GetCoinInfoUseCase
import com.masorone.cryptocompare.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class CoinPriceListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    init {
        viewModelScope.launch {
            loadDataUseCase.invoke()
        }
    }

    val coinInfoList = getCoinInfoListUseCase.invoke()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase.invoke(fSym)
}