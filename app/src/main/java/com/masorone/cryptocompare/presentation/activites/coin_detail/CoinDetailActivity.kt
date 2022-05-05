package com.masorone.cryptocompare.presentation.activites.coin_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.masorone.cryptocompare.databinding.ActivityCoinDetailBinding
import com.masorone.cryptocompare.presentation.App
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CoinDetailActivity : AppCompatActivity() {

    private var fSym = ""

    lateinit var binding: ActivityCoinDetailBinding

    @Inject
    lateinit var vm: CoinDetailViewModel

    private val appComponent by lazy {
        (application as App).appComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appComponent.inject(this)
        init()
    }

    private fun init() {
        fSym = intent.getStringExtra(COIN_FROM_SYMBOL) ?: ""
        observeDetailInfo(fSym)
    }

    private fun observeDetailInfo(fSym: String) {
        vm.getDetailInfo(fSym).observe(this) {
            binding.tvFSym.text = it.fromSymbol
            binding.tvTSym.text = it.toSymbol
            binding.priceDetail.text = String.format("%.2f", it.price.toFloat())
            binding.minPriceDetail.text = String.format("%.2f", it?.lowDay?.toFloat())
            binding.maxPriceDetail.text = String.format("%.2f", it?.highDay?.toFloat())
            binding.lastMarketDetail.text = it.lastMarket
            binding.timeDetail.text = it.lastUpdate
            Picasso.get().load(it.imageUrl).into(binding.ivCoinLogo)
        }
    }

    companion object {

        private const val COIN_FROM_SYMBOL = "coin_from_symbol"

        fun newIntent(context: Context, fSym: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(COIN_FROM_SYMBOL, fSym)
            return intent
        }
    }
}