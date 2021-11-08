package com.masorone.cryptocompare

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.masorone.cryptocompare.api.ApiFactory
import com.masorone.cryptocompare.db.CoinDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(context: Context) : ViewModel() {

    private var compositeDisposable = CompositeDisposable()
    private val db = CoinDatabase.getInstance(context)
    val priceList = db.coinPriceInfoDao().getPriceList()

    fun loadData() {
        compositeDisposable.add(
            ApiFactory.apiService.getTopCoinsInfo()
                .map { it.data?.map { datum -> datum.coinInfo?.name }?.joinToString(",") }
                .flatMap { ApiFactory.apiService.getFullPriceList(fSyms = it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({


                    Log.d(TAG, "CoinPriceInfoRawData -> $it")
                }, {
                    Log.d(TAG, "Error(CoinPriceInfoRawData) -> $it")
                })
        )
    }

    companion object {
        private const val TAG = "MainViewModel"
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}