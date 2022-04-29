package com.masorone.cryptocompare.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.masorone.cryptocompare.domain.CoinInfo

class CoinInfoDiffUtil : DiffUtil.ItemCallback<CoinInfo>() {

    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem == newItem
    }
}