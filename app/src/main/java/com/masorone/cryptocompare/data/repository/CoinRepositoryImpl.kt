package com.masorone.cryptocompare.data.repository

import android.app.Application
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.masorone.cryptocompare.data.database.CoinDatabase
import com.masorone.cryptocompare.data.mapper.CoinMapper
import com.masorone.cryptocompare.data.network.ApiFactory
import com.masorone.cryptocompare.data.worker.RefreshDataWorker
import com.masorone.cryptocompare.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(
    private val application: Application
) : CoinRepository {

    private val coinInfoDao = CoinDatabase.getInstance(application).coinPriceInfoDao()
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

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}