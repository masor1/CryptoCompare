package com.masorone.cryptocompare

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.masorone.cryptocompare.adapters.CoinInfoAdapter
import com.masorone.cryptocompare.pojo.CoinPriceInfo

class MainActivity : AppCompatActivity() {

    private val coinInfoAdapter = CoinInfoAdapter()
    private lateinit var vm: MainViewModel
    private lateinit var rvCoinPriceList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        init()
    }

    private fun init() {
        rvCoinPriceList = findViewById(R.id.rvCoinPriceList)
        coinInfoAdapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coin: CoinPriceInfo) {
                Toast.makeText(applicationContext, coin.fromSymbol, Toast.LENGTH_SHORT).show()
            }
        }
        rvCoinPriceList.adapter = coinInfoAdapter
        observePriceList()
    }

    private fun initViewModel() {
        vm = ViewModelProvider(
            this,
            MainViewModelFactory(context = applicationContext)
        )[MainViewModel::class.java]
    }

    private fun observePriceList() {
        vm.priceList.observe(this, {
            coinInfoAdapter.coinInfoList = it
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