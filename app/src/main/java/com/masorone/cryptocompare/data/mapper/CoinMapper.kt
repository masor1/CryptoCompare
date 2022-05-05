package com.masorone.cryptocompare.data.mapper

import com.google.gson.Gson
import com.masorone.cryptocompare.data.database.CoinInfoDb
import com.masorone.cryptocompare.data.network.model.CoinInfoDto
import com.masorone.cryptocompare.data.network.model.CoinInfoJsonContainerDto
import com.masorone.cryptocompare.data.network.model.CoinNameListDto
import com.masorone.cryptocompare.domain.CoinInfo
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CoinMapper @Inject constructor() {

    fun mapDtoToDbModel(dto: CoinInfoDto) = CoinInfoDb(
        dto.fromSymbol,
        dto.toSymbol,
        dto.price,
        dto.lastUpdate.toLong(),
        dto.highDay,
        dto.lowDay,
        dto.lastMarket,
        BASE_IMAGE_URL + dto.imageUrl
    )

    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapNamesListToString(namesListDto: CoinNameListDto) =
        namesListDto.names?.map { coinNameContainer ->
            coinNameContainer.coinNameDto?.name
        }?.joinToString(",") ?: ""

    fun mapDbToEntity(db: CoinInfoDb) = CoinInfo(
        db.fromSymbol,
        db.toSymbol,
        db.price.toString(),
        convertTimestampToTime(db.lastUpdate),
        db.highDay.toString(),
        db.lowDay.toString(),
        db.lastMarket,
        db.imageUrl
    )

    private fun convertTimestampToTime(timestamp: Long?): String {
        if (timestamp == null) return ""
        val stamp = Timestamp(timestamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    companion object {

        private const val BASE_IMAGE_URL = "https://cryptocompare.com/"
    }
}