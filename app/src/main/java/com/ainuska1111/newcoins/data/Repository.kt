package com.ainuska1111.newcoins.data

import com.ainuska1111.newcoins.data.network.api.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor ( var repo: ApiService) {
    fun getCoin(currency: String) = repo.getCoins()
}