package com.ainuska1111.newcoins

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ainuska1111.newcoins.data.Repository
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
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val coinLiveData = MutableLiveData<Resource<CoinResponse>>()
    val errorLiveData = MutableLiveData<Throwable>()
    var disposables = CompositeDisposable()


        fun loadCoin(currency: String) {
            coinLiveData.value = Resource.loading()
            disposables.add(
                    repository.getCoin(currency).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                coinLiveData.postValue(Resource.success(it))

                            }, {
                                it.printStackTrace()
                                errorLiveData.postValue(it)
                            })
            )

        }

        override fun onCleared() {
            disposables.clear()
            super.onCleared()
        }

    }
