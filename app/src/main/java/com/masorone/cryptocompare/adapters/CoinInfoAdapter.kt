package com.masorone.cryptocompare.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.masorone.cryptocompare.R
import com.masorone.cryptocompare.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter : RecyclerView.Adapter<CoinInfoAdapter.CoinViewHolder>() {

    var coinInfoList = listOf<CoinPriceInfo>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        return CoinViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_coin_info,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = coinInfoList[position]
        holder.bind(coin = coin)
    }

    override fun getItemCount() = coinInfoList.size

    inner class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivLogoCoin: ImageView = itemView.findViewById(R.id.ivLogoCoin)
        private val tvSymbols: TextView = itemView.findViewById(R.id.tvSymbols)
        private val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        private val tvTime: TextView = itemView.findViewById(R.id.tvTime)

        fun bind(coin: CoinPriceInfo) {
            tvSymbols.text = String.format(
                itemView.resources.getString(R.string.symbols_template),
                coin.fromSymbol,
                coin.toSymbol
            )
            tvPrice.text = coin.price.toString()
            tvTime.text = coin.getFormattedTime()
            Picasso.get().load(coin.getFullImageUrl()).into(ivLogoCoin)
            itemView.setOnClickListener {
                onCoinClickListener?.onCoinClick(coin)
            }
        }
    }

    interface OnCoinClickListener {
        fun onCoinClick(coin: CoinPriceInfo)
    }
}