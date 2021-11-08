package com.masorone.cryptocompare.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.masorone.cryptocompare.pojo.CoinPriceInfo

@Database(entities = [CoinPriceInfo::class], version = 1, exportSchema = false)
abstract class CoinDatabase : RoomDatabase() {
    companion object {

        private const val DB_NAME = "coin.db"
        private val LOCK = Any()
        private var db: CoinDatabase? = null

        fun getInstance(context: Context): CoinDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(context, CoinDatabase::class.java, DB_NAME).build()
                db = instance
                return instance
            }
        }
    }

    abstract fun coinPriceInfoDao(): CoinPriceInfoDao
}