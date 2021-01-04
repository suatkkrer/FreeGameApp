package com.suatkkrer.freegameapp.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Game(
        @ColumnInfo(name = "title")
        @SerializedName("title")
        val gameName: String?,

        @ColumnInfo(name = "id")
        @SerializedName("id")
        val id: Int?,

        @ColumnInfo(name = "thumbnail")
        @SerializedName("thumbnail")
        val imgUrl: String?,

        @ColumnInfo(name = "short_description")
        @SerializedName("short_description")
        val description: String?,

        @ColumnInfo(name = "game_url")
        @SerializedName("game_url")
        val game_url: String?,

        @ColumnInfo(name = "genre")
        @SerializedName("genre")
        val genre: String?,

        @ColumnInfo(name = "publisher")
        @SerializedName("publisher")
        val publisher: String?,

        @ColumnInfo(name = "release_date")
        @SerializedName("release_date")
        val release_date: String?) {

        @PrimaryKey(autoGenerate = true)
        var uuid: Int = 0
}