package com.masorone.cryptocompare.data.mapper

import com.google.gson.Gson
import com.masorone.cryptocompare.data.database.CoinInfoDb
import com.masorone.cryptocompare.data.network.model.CoinInfoDto
import com.masorone.cryptocompare.data.network.model.CoinInfoJsonContainerDto
import com.masorone.cryptocompare.data.network.model.CoinNameListDto
import com.masorone.cryptocompare.domain.CoinInfo

class CoinMapper {

    fun mapDtoToDbModel(dto: CoinInfoDto) = CoinInfoDb(
        dto.fromSymbol,
        dto.toSymbol,
        dto.price,
        dto.lastUpdate,
        dto.highDay,
        dto.lowDay,
        dto.lastMarket,
        dto.imageUrl
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
        db.price,
        db.lastUpdate,
        db.highDay,
        db.lowDay,
        db.lastMarket,
        db.imageUrl
    )
}