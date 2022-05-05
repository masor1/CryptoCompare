package com.masorone.cryptocompare.presentation.activites.coin_price_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.masorone.cryptocompare.databinding.ActivityCoinPriceListBinding
import com.masorone.cryptocompare.domain.CoinInfo
import com.masorone.cryptocompare.presentation.App
import com.masorone.cryptocompare.presentation.activites.coin_detail.CoinDetailActivity
import com.masorone.cryptocompare.presentation.adapters.CoinInfoAdapter
import javax.inject.Inject

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinPriceListBinding

    private lateinit var coinInfoAdapter: CoinInfoAdapter

    @Inject
    lateinit var vm: CoinPriceListViewModel

    private val appComponent by lazy {
        (application as App).appComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appComponent.inject(this)
        init()
    }

    private fun init() {
        coinInfoAdapter = CoinInfoAdapter()
        binding.rvCoinPriceList.adapter = coinInfoAdapter

        observePriceList()

        coinInfoAdapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinDto: CoinInfo) {
                intent =
                    CoinDetailActivity.newIntent(this@CoinPriceListActivity, coinDto.fromSymbol)
                startActivity(intent)
            }
        }
    }

    private fun observePriceList() {
        vm.coinInfoList.observe(this) {
            coinInfoAdapter.submitList(it)
        }
    }
}