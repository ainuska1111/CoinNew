package com.ainuska1111.newcoins.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ainuska1111.newcoins.data.network.api.ApiService
import com.ainuska1111.newcoins.data.network.model.CoinResponse
import com.ainuska1111.newcoins.data.network.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CoinViewModel @Inject constructor(private val service: ApiService) : ViewModel() {
    val coinLiveData = MutableLiveData<Resource<CoinResponse>>()

    fun getCoinScreen() {
        coinLiveData.postValue(Resource.loading())

    }
}