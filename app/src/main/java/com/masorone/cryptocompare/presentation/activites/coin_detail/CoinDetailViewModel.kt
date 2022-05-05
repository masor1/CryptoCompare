package com.masorone.cryptocompare.presentation.activites.coin_detail

import androidx.lifecycle.ViewModel
import com.masorone.cryptocompare.domain.GetCoinInfoUseCase
import javax.inject.Inject

class CoinDetailViewModel @Inject constructor(
    private val getCoinInfoUseCase: GetCoinInfoUseCase
) : ViewModel() {

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase.invoke(fSym)
}