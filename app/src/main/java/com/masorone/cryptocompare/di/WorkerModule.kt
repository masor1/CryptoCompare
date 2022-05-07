package com.masorone.cryptocompare.di

import com.masorone.cryptocompare.data.worker.ChildWorkerFactory
import com.masorone.cryptocompare.data.worker.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshDataWorkerFactory(refreshDataWorker: RefreshDataWorker.Factory): ChildWorkerFactory
}