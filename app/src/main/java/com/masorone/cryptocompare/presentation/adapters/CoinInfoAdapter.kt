package com.masorone.cryptocompare.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.masorone.cryptocompare.R
import com.masorone.cryptocompare.databinding.ItemCoinInfoBinding
import com.masorone.cryptocompare.domain.CoinInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter : ListAdapter<CoinInfo, CoinInfoAdapter.CoinViewHolder>(CoinInfoDiffUtil()) {

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CoinViewHolder(
        ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = getItem(position)
        holder.bind(coin)
    }

    inner class CoinViewHolder(
        private val binding: ItemCoinInfoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(coinDto: CoinInfo) = with(binding) {
            tvSymbols.text = String.format(
                itemView.resources.getString(R.string.symbols_template),
                coinDto.fromSymbol,
                coinDto.toSymbol
            )
            tvPrice.text = coinDto.price
            tvTime.text = coinDto.lastUpdate
            root.setOnClickListener {
                onCoinClickListener?.onCoinClick(coinDto)
            }
            Picasso.get().load(coinDto.imageUrl).into(ivLogoCoin)
        }
    }

    interface OnCoinClickListener {

        fun onCoinClick(coinDto: CoinInfo)
    }
}