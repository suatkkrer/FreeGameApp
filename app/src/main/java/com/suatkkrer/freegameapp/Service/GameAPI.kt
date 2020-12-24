package com.suatkkrer.freegameapp.Service

import com.suatkkrer.freegameapp.Model.Game
import io.reactivex.Single
import retrofit2.http.GET

interface GameAPI {

    //https://www.freetogame.com/api/games

    @GET("api/games")
    fun getGames():Single<List<Game>>

}