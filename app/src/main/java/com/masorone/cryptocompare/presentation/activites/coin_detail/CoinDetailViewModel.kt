package com.masorone.cryptocompare.presentation.activites.coin_detail

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.masorone.cryptocompare.data.database.CoinDatabase
import com.masorone.cryptocompare.data.network.model.CoinInfoDto
import com.masorone.cryptocompare.data.repository.CoinRepositoryImpl
import com.masorone.cryptocompare.domain.CoinInfo
import com.masorone.cryptocompare.domain.GetCoinInfoUseCase

class CoinDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase.invoke(fSym)
}