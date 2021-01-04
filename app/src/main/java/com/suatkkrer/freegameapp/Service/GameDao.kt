package com.suatkkrer.freegameapp.Service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.suatkkrer.freegameapp.Model.Game

@Dao
interface GameDao {


    @Insert
    suspend fun insertAll(vararg games : Game) : List<Long>


    @Query("SELECT * FROM game")
    suspend fun getAllGames() : List<Game>

    @Query("SELECT * FROM game WHERE uuid = :gameId")
    suspend fun getGame(gameId: Int) : Game

    @Query("DELETE FROM game")
    suspend fun deleteALLCountries()


}