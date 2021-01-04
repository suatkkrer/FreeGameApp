 package com.suatkkrer.freegameapp.Service

import com.google.gson.Gson
import com.suatkkrer.freegameapp.Model.Game
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GameAPIService {

    //https://www.freetogame.com/api/games

    private val BASE_URL = "https://www.freetogame.com/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(GameAPI::class.java)

    fun getData() : Single<List<Game>> {
        return api.getGames()
    }

}