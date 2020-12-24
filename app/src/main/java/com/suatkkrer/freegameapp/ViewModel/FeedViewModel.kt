package com.suatkkrer.freegameapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suatkkrer.freegameapp.Model.Game

class FeedViewModel : ViewModel() {

    val games = MutableLiveData<List<Game>>()
    val gameError = MutableLiveData<Boolean>()
    val gameLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        val game = Game("oyun",1,"www.ccc.com","dsafasdfa","sdafasfdasf","agasdga","dasfasf","safsadfasf")
        val game2 = Game("sdfafd",1,"www.ccc.com","dsafasdfa","sdafasfdasf","agasdga","dasfasf","safsadfasf")
        val game3 = Game("oysdfaaun",1,"www.ccc.com","dsafasdfa","sdafasfdasf","agasdga","dasfasf","safsadfasf")


        val gameList = arrayListOf<Game>(game,game2,game3)

        games.value = gameList
        gameError.value = false
        gameLoading.value = false

    }
}