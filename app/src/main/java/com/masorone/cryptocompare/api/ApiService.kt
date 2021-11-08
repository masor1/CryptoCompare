package com.masorone.cryptocompare.api

import com.masorone.cryptocompare.pojo.CoinInfoListOfData
import com.masorone.cryptocompare.pojo.CoinPriceInfoRawData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_LIMIT) limit: Int = DEFAULT_LIMIT,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = DEFAULT_CURRENCY
    ): Single<CoinInfoListOfData>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String = DEFAULT_CRYPTO_CURRENCY,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSym: String = DEFAULT_CURRENCY
    ): Single<CoinPriceInfoRawData>

    companion object {
        private const val API_KEY = "5d16ba9d569d3b2664a879c643367be6210f3f9926a0b05512b9a608dc9777e6"

        private const val DEFAULT_LIMIT = 30
        private const val DEFAULT_CURRENCY = "USD"
        private const val DEFAULT_CRYPTO_CURRENCY = "BTC,ETH,EOS"

        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
    }
}