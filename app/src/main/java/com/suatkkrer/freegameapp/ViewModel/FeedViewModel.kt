package com.suatkkrer.freegameapp.ViewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suatkkrer.freegameapp.Model.Game
import com.suatkkrer.freegameapp.Service.GameAPIService
import com.suatkkrer.freegameapp.Service.GameDatabase
import com.suatkkrer.freegameapp.Util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : BaseViewModel(application) {

    private val gameAPIService = GameAPIService()
    private val disposable = CompositeDisposable()
    private var customPreferences = CustomSharedPreferences(getApplication())

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
                        storeInSQLite(t)
                    }

                    override fun onError(e: Throwable) {
                        gameError.value = true
                        gameLoading.value = false
                        e.printStackTrace()

                    }
                })
        )
    }

    private fun showGames(gameList: List<Game>){
        games.value = gameList
        gameError.value = false
        gameLoading.value = false
    }
    private fun storeInSQLite(list: List<Game>){
        launch {
            val dao = GameDatabase(getApplication()).gameDao()
            dao.deleteALLCountries()
            val listLong = dao.insertAll(*list.toTypedArray())

            var i = 0

            while (i < list.size){
                list[i].uuid = listLong[i].toInt()
                i += 1
            }
            showGames(list)
        }

        customPreferences.saveTime(System.nanoTime())

    }

}