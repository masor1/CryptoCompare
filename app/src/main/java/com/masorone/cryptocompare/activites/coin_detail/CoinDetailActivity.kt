package com.masorone.cryptocompare.activites.coin_detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.masorone.cryptocompare.R

class CoinDetailActivity : AppCompatActivity() {

    private var fSym = ""
    private lateinit var vm: CoinDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        init()
    }

    private fun initViewModel() {
        vm = ViewModelProvider(this, CoinDetailViewModelFactory(this))[CoinDetailViewModel::class.java]
    }

    private fun init() {
        initViewModel()
        fSym = intent.getStringExtra(COIN_FROM_SYMBOL) ?: ""
        observeDetailInfo(fSym = fSym)
    }

    private fun observeDetailInfo(fSym: String) {
        vm.getDetailInfo(fSym).observe(this, {
            Log.d(TAG, "CoinPriceInfo -> $it")
        })
    }

    companion object {
        private const val COIN_FROM_SYMBOL = "coin_from_symbol"
        private const val TAG = "CoinDetailActivity"

        fun newIntent(context: Context, fSym: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(COIN_FROM_SYMBOL, fSym)
            return intent
        }
    }
}