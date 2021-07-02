package com.ainuska1111.newcoins.data.network.model

import com.google.gson.annotations.SerializedName


data class CoinResponse(
    @SerializedName("coins")
    val coins: List<CoinItem>,
    @SerializedName("supportedExchanges")
    val supportedExchanges: List<String>
)