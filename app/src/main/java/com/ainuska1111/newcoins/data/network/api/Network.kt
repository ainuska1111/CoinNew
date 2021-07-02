package com.ainuska1111.newcoins.data.network.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Network {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.coinstats.app/public/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    private var service: ApiService? = null

    fun getService(): ApiService {
        if (service == null) {
            service = retrofit.create(ApiService::class.java)
        }
        return service!!
    }
}