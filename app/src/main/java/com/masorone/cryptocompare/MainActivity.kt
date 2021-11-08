package com.masorone.cryptocompare

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        observePriceList()
        observeDetailInfo()
    }

    private fun initViewModel() {
        vm = ViewModelProvider(
            this,
            MainViewModelFactory(context = applicationContext)
        )[MainViewModel::class.java]
    }

    private fun observePriceList() {
        vm.priceList.observe(this, {
            Log.d(TAG, "List<CoinPriceInfo> -> $it")
        })
    }

    private fun observeDetailInfo() {
        vm.getDetailInfo("BTC").observe(this, {
            Log.d(TAG, "CoinPriceInfo -> $it")
        })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}