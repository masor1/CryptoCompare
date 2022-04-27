package com.masorone.cryptocompare.presentation.activites.coin_price_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.masorone.cryptocompare.R
import com.masorone.cryptocompare.presentation.activites.coin_detail.CoinDetailActivity
import com.masorone.cryptocompare.presentation.adapters.CoinInfoAdapter
import com.masorone.cryptocompare.data.network.model.CoinInfoDto
import com.masorone.cryptocompare.domain.CoinInfo

class CoinPriceListActivity : AppCompatActivity() {

    private val coinInfoAdapter = CoinInfoAdapter()
    private lateinit var vm: CoinPriceListViewModel
    private lateinit var rvCoinPriceList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        initViewModel()
        init()
    }

    private fun init() {
        rvCoinPriceList = findViewById(R.id.rvCoinPriceList)
        rvCoinPriceList.adapter = coinInfoAdapter
        observePriceList()
        coinInfoAdapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinDto: CoinInfo) {
                intent =
                    CoinDetailActivity.newIntent(this@CoinPriceListActivity, coinDto.fromSymbol)
                startActivity(intent)
            }
        }
    }

    private fun initViewModel() {
        vm = ViewModelProvider(this)[CoinPriceListViewModel::class.java]
    }

    private fun observePriceList() {
        vm.coinInfoList.observe(this) {
            coinInfoAdapter.coinInfoList = it
        }
    }

    companion object {
        private const val TAG = "CoinPriceListActivity"
    }
}