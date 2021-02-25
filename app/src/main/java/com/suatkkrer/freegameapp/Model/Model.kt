package com.suatkkrer.freegameapp.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Game(
        @ColumnInfo(name = "title")
        @SerializedName("title")
        var gameName: String?,

//        @ColumnInfo(name = "id")
//        @SerializedName("id")
//        var id: Int?,

        @ColumnInfo(name = "thumbnail")
        @SerializedName("thumbnail")
        var thumbnail: String?,

        @ColumnInfo(name = "short_description")
        @SerializedName("short_description")
        var description: String?,

        @ColumnInfo(name = "game_url")
        @SerializedName("game_url")
        var game_url: String?,

        @ColumnInfo(name = "genre")
        @SerializedName("genre")
        var genre: String?,

        @ColumnInfo(name = "publisher")
        @SerializedName("publisher")
        var publisher: String?,

        @ColumnInfo(name = "release_date")
        @SerializedName("release_date")
        var release_date: String?) {

        @PrimaryKey(autoGenerate = true)
        var uuid: Int = 0
}