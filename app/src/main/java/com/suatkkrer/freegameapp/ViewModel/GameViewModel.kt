package com.suatkkrer.freegameapp.ViewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suatkkrer.freegameapp.Model.Game
import com.suatkkrer.freegameapp.Service.GameDatabase
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : BaseViewModel(application) {

    val gameLiveData = MutableLiveData<Game>()

    fun getDataFromRoom(uuid : Int) {
        launch {

            val dao = GameDatabase(getApplication()).gameDao()
            val game = dao.getGame(uuid)
            gameLiveData.value = game

        }

    }


}