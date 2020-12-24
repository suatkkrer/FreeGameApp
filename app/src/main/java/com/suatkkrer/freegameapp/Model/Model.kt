package com.suatkkrer.freegameapp.Model

import com.google.gson.annotations.SerializedName


data class Game(

        @SerializedName("title")
        val gameName: String?,

        @SerializedName("id")
        val id: Int?,

        @SerializedName("thumbnail")
        val imgUrl: String?,

        @SerializedName("short_description")
        val description: String?,

        @SerializedName("game_url")
        val game_url: String?,

        @SerializedName("genre")
        val genre: String?,

        @SerializedName("publisher")
        val publisher: String?,

        @SerializedName("release_date")
        val release_date: String?)