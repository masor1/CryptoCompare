package com.masorone.cryptocompare.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.masorone.cryptocompare.data.database.CoinDatabase
import com.masorone.cryptocompare.data.mapper.CoinMapper
import com.masorone.cryptocompare.data.network.ApiFactory
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    private val coinInfoDao = CoinDatabase.getInstance(context).coinPriceInfoDao()
    private val apiService = ApiFactory.apiService

    private val mapper = CoinMapper()

    override suspend fun doWork(): Result {
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

    companion object {

        const val NAME = "RefreshDataWorker"

        fun makeRequest() = OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
    }
}