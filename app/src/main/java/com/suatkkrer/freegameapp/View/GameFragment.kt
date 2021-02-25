package com.suatkkrer.freegameapp.View

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.suatkkrer.freegameapp.View.GameFragmentArgs
import com.suatkkrer.freegameapp.R
import com.suatkkrer.freegameapp.Util.downloadFromUrl
import com.suatkkrer.freegameapp.Util.placeholderProgressBar
import com.suatkkrer.freegameapp.ViewModel.GameViewModel
import kotlinx.android.synthetic.main.fragment_game.*


class GameFragment : Fragment() {

    private lateinit var viewModel : GameViewModel

    private var gameUuid = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            gameUuid = GameFragmentArgs.fromBundle(it).gameUuid
        }

        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        viewModel.getDataFromRoom(gameUuid)
        observeLiveData()

        viewModel.gameLiveData.observe(viewLifecycleOwner, Observer {game ->
            game?.let {
                game_url.setOnClickListener {
                    val browse = Intent(Intent.ACTION_VIEW, Uri.parse(game.game_url))
                    startActivity(browse)
                }
            }
        })


    }

    @SuppressLint("SetTextI18n")
    private fun observeLiveData() {
        viewModel.gameLiveData.observe(viewLifecycleOwner, Observer { game ->
            game?.let {
                gameName.text =  "${game.gameName}"
                genreText.text = "${game.genre}"
                release_date.text = "${game.release_date}"
                description.text = "${game.description}"
                game_url.underline()
                game_url.text = "${game.game_url}"
                publisher.text = "${game.publisher}"
                context?.let {
                    gameImage.downloadFromUrl(game.thumbnail, placeholderProgressBar(it))
                }
            }
        })
    }
    fun TextView.underline(){
        paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }
}