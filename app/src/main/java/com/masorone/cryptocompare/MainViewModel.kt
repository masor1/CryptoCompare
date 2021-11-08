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

    fun getTopCoinsInfo() {
        compositeDisposable.add(
            ApiFactory.apiService.getTopCoinsInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val names = it.data?.map { datum -> datum.coinInfo?.name }?.joinToString(",")
                    Log.d(TAG, "names -> $names")
                }, {
                    Log.d(TAG, "Error(CoinInfoListOfData) -> $it")
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