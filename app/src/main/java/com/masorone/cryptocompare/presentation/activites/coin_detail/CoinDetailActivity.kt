package com.masorone.cryptocompare.presentation.activites.coin_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.masorone.cryptocompare.R
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var ivCoinLogo: ImageView
    private lateinit var tvFSym: TextView
    private lateinit var tvTSym: TextView
    private lateinit var tvPrice: TextView
    private lateinit var tvMinPrice: TextView
    private lateinit var tvMaxPrice: TextView
    private lateinit var tvLastMarket: TextView
    private lateinit var tvTime: TextView
    private var fSym = ""

    private lateinit var vm: CoinDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        init()
    }

    private fun initViewModel() {
        vm = ViewModelProvider(this)[CoinDetailViewModel::class.java]
    }

    private fun init() {
        ivCoinLogo = findViewById(R.id.ivCoinLogo)
        tvFSym = findViewById(R.id.tvFSym)
        tvTSym = findViewById(R.id.tvTSym)
        tvPrice = findViewById(R.id.price_detail)
        tvMinPrice = findViewById(R.id.min_price_detail)
        tvMaxPrice = findViewById(R.id.max_price_detail)
        tvLastMarket = findViewById(R.id.lastMarketDetail)
        tvTime = findViewById(R.id.time_detail)

        initViewModel()

        fSym = intent.getStringExtra(COIN_FROM_SYMBOL) ?: ""
        observeDetailInfo(fSym)
    }

    private fun observeDetailInfo(fSym: String) {

        vm.getDetailInfo(fSym).observe(this) {
            tvFSym.text = it.fromSymbol
            tvTSym.text = it.toSymbol
            tvPrice.text = String.format("%.2f", it.price.toFloat())
            tvMinPrice.text = String.format("%.2f", it?.lowDay?.toFloat())
            tvMaxPrice.text = String.format("%.2f", it?.highDay?.toFloat())
            tvLastMarket.text = it.lastMarket
            tvTime.text = it.lastUpdate
            Picasso.get().load(it.imageUrl).into(ivCoinLogo)
        }
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