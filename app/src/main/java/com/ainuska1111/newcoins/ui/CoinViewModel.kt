package com.ainuska1111.newcoins.ui

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ainuska1111.newcoins.data.network.api.ApiService
import com.ainuska1111.newcoins.data.network.api.CoinType
import com.ainuska1111.newcoins.data.network.api.Network
import com.ainuska1111.newcoins.data.network.model.CoinResponse
import com.ainuska1111.newcoins.data.network.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class CoinViewModel @Inject constructor(private val service: ApiService) : ViewModel() {
    val coinLiveData = MutableLiveData<CoinResponse>()
    val errorLiveData = MutableLiveData<Throwable>()

    var disposables = CompositeDisposable()


    @SuppressLint("CheckResult")
    fun loadData(type: String) {
        val observable = when (type) {
            CoinType.COINS.name -> Network.getService().getCoins()
            else -> Network.getService().getCoins()

        }
        disposables.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    coinLiveData.postValue(it)

                }, {
                    it.printStackTrace()
                    errorLiveData.postValue(it)
                }))
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}