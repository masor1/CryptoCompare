package com.masorone.cryptocompare.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masorone.cryptocompare.R
import com.masorone.cryptocompare.databinding.ItemCoinInfoBinding
import com.masorone.cryptocompare.domain.CoinInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter : RecyclerView.Adapter<CoinInfoAdapter.CoinViewHolder>() {

    var coinInfoList = listOf<CoinInfo>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        return CoinViewHolder(
            ItemCoinInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = coinInfoList[position]
        holder.bind(coin)
    }

    override fun getItemCount() = coinInfoList.size

    inner class CoinViewHolder(private val binding: ItemCoinInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(coinDto: CoinInfo) {
            binding.tvSymbols.text = String.format(
                itemView.resources.getString(R.string.symbols_template),
                coinDto.fromSymbol,
                coinDto.toSymbol
            )
            binding.tvPrice.text = coinDto.price
            binding.tvTime.text = coinDto.lastUpdate

            Picasso.get().load(coinDto.imageUrl).into(binding.ivLogoCoin)

            itemView.setOnClickListener {
                onCoinClickListener?.onCoinClick(coinDto)
            }
        }
    }

    interface OnCoinClickListener {

        fun onCoinClick(coinDto: CoinInfo)
    }
}