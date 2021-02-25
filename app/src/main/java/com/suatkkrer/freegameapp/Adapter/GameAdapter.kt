package com.suatkkrer.freegameapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.suatkkrer.freegameapp.Model.Game
import com.suatkkrer.freegameapp.R
import com.suatkkrer.freegameapp.Util.downloadFromUrl
import com.suatkkrer.freegameapp.Util.placeholderProgressBar
import com.suatkkrer.freegameapp.View.MainFragmentDirections
import kotlinx.android.synthetic.main.item_game.view.*
import java.util.ArrayList

class GameAdapter(val gameList: ArrayList<Game>) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    class GameViewHolder(var view:View) : RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_game,parent,false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.view.name.text = gameList[position].gameName
        holder.view.genre.text = gameList[position].genre
        holder.view.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToGameFragment()
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.imageView.downloadFromUrl(gameList[position].thumbnail, placeholderProgressBar(holder.view.context))

    }

    override fun getItemCount(): Int {
       return gameList.size
    }
    fun updateGameList(newGameList: List<Game>){
        gameList.clear()
        gameList.addAll(newGameList)
        notifyDataSetChanged()
    }


}