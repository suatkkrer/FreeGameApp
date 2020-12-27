package com.suatkkrer.freegameapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suatkkrer.freegameapp.Model.Game
import com.suatkkrer.freegameapp.Service.GameAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel() {

    private val gameAPIService = GameAPIService()
    private val disposable = CompositeDisposable()

    val games = MutableLiveData<List<Game>>()
    val gameError = MutableLiveData<Boolean>()
    val gameLoading = MutableLiveData<Boolean>()

    fun refreshData(){

        getDataFromAPI()

    }

    private fun getDataFromAPI(){
        gameLoading.value = true

        disposable.add(
            gameAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object  : DisposableSingleObserver<List<Game>>(){
                    override fun onSuccess(t: List<Game>) {
                        games.value = t
                        gameError.value = false
                        gameLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        gameError.value = true
                        gameLoading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }
}