package com.masorone.cryptocompare.activites.coin_detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.masorone.cryptocompare.db.CoinDatabase
import com.masorone.cryptocompare.pojo.CoinPriceInfo
import io.reactivex.disposables.CompositeDisposable

class CoinDetailViewModel(context: Context) : ViewModel() {

    private val db = CoinDatabase.getInstance(context)

    fun getDetailInfo(fSym: String): LiveData<CoinPriceInfo> {
        return db.coinPriceInfoDao().getPriceInfoAboutCoin(fSym = fSym)
    }
}