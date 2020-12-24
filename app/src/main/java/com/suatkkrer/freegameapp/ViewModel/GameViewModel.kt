package com.suatkkrer.freegameapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suatkkrer.freegameapp.Model.Game

class GameViewModel : ViewModel() {

    val gameLiveData = MutableLiveData<Game>()

    fun getDataFromRoom() {
        val game = Game("oyun",1,"www.ccc.com","dsafasdfa","sdafasfdasf","agasdga","dasfasf","safsadfasf")
        gameLiveData.value = game
    }


}