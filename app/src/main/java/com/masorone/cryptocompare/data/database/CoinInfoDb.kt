package com.masorone.cryptocompare.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.masorone.cryptocompare.data.network.ApiFactory.BASE_IMAGE_URL
import com.masorone.cryptocompare.utils.convertTimestampToTime

@Entity(tableName = "full_price_list")
data class CoinInfoDb(
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String? = null,
    val price: Double? = null,
    val lastUpdate: Int? = null,
    val highDay: Double? = null,
    val lowDay: Double? = null,
    val lastMarket: String? = null,
    val imageUrl: String? = null
)