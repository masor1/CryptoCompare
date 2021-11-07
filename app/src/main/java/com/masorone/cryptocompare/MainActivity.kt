package com.masorone.cryptocompare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.masorone.cryptocompare.api.ApiFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getTopCoinsInfoInLog()
        getFullPriceListInLog()
    }

    private fun getTopCoinsInfoInLog() {
        compositeDisposable.add(
            ApiFactory.apiService.getTopCoinsInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d(TAG, "CoinInfoListOfData -> $it")
                }, {
                    Log.d(TAG, "Error(CoinInfoListOfData) -> $it")
                })
        )
    }

    private fun getFullPriceListInLog() {
        compositeDisposable.add(
            ApiFactory.apiService.getFullPriceList()
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
        private const val TAG = "MainActivity"
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}