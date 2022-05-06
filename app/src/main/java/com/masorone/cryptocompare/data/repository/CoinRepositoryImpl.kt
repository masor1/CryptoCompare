package com.masorone.cryptocompare.data.repository

import android.content.Context
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.masorone.cryptocompare.data.database.CoinDatabase
import com.masorone.cryptocompare.data.mapper.CoinMapper
import com.masorone.cryptocompare.data.worker.RefreshDataWorker
import com.masorone.cryptocompare.domain.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    database: CoinDatabase,
    private val context: Context,
    private val mapper: CoinMapper
) : CoinRepository {

    private val coinInfoDao = database.coinPriceInfoDao()

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
        val workManager = WorkManager.getInstance(context)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}