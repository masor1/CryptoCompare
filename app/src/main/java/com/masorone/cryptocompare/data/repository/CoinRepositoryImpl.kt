package com.masorone.cryptocompare.data.repository

import android.app.Application
import androidx.lifecycle.Transformations
import com.masorone.cryptocompare.data.database.CoinDatabase
import com.masorone.cryptocompare.data.mapper.CoinMapper
import com.masorone.cryptocompare.data.network.ApiFactory
import com.masorone.cryptocompare.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(
    application: Application
) : CoinRepository {

    private val coinInfoDao = CoinDatabase.getInstance(application).coinPriceInfoDao()
    private val apiService = ApiFactory.apiService

    private val mapper = CoinMapper()

    override fun getCoinInfoList() =
        Transformations.map(coinInfoDao.getPriceList()) { listCoinInfoDb ->
            listCoinInfoDb.map { coinInfoDb ->
                mapper.mapDbToEntity(coinInfoDb)
            }
        }

    override fun getCoinInfo(fromSymbol: String) =
        Transformations.map(coinInfoDao.getPriceInfoAboutCoin(fromSymbol)) { coinInfoDb ->
            mapper.mapDbToEntity(coinInfoDb)
        }

    override suspend fun loadData() {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo()
                val fSyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val coinDbList = coinInfoDtoList.map { coinInfoDto ->
                    mapper.mapDtoToDbModel(coinInfoDto)
                }
                coinInfoDao.insertPriceList(coinDbList)
            } catch (e: Exception) {
            }
            delay(10_000)
        }
    }
}