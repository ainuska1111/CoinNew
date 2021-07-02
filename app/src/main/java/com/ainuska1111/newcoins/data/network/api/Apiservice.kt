package com.ainuska1111.newcoins.data.network.api

import com.ainuska1111.newcoins.data.network.model.CoinResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    companion object {
        const val BASE_URL = "https://api.coinstats.app/public/v1/"
    }

    @GET("v1/coins")
    fun getCoins(
            @Query("limit") limit: Int = 20,
            @Query("skip") skip: Int = 0

    ): io.reactivex.Observable<CoinResponse>


    @GET("v1/exchanges")
    fun getExchanges(): io.reactivex.Observable<CoinResponse>


}