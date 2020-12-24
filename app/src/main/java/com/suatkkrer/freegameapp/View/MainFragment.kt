package com.suatkkrer.freegameapp.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.suatkkrer.freegameapp.Adapter.GameAdapter
import com.suatkkrer.freegameapp.R
import com.suatkkrer.freegameapp.ViewModel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var viewModel : FeedViewModel
    private val gameAdapter = GameAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        fragmentButton.setOnClickListener {
//            val action = MainFragmentDirections.actionMainFragmentToGameFragment()
//            Navigation.findNavController(it).navigate(action)
//        }


        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        gameList.layoutManager = GridLayoutManager(context,2)
        gameList.adapter = gameAdapter

        swipeRefreshLayout.setOnRefreshListener {
            gameList.visibility = View.GONE
            gameError.visibility = View.GONE
            gameLoading.visibility = View.GONE
            viewModel.refreshData()
            swipeRefreshLayout.isRefreshing = false
        }

        obserbeLiveData()


    }

    private fun obserbeLiveData() {
        viewModel.games.observe(viewLifecycleOwner, Observer { games ->

            games?.let {
                gameList.visibility = View.VISIBLE
                gameAdapter.updateGameList(games)
            }
        })
        viewModel.gameError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it){
                    gameError.visibility = View.VISIBLE
                } else {
                    gameError.visibility = View.GONE
                }
            }
        })
        viewModel.gameLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it){
                    gameLoading.visibility = View.VISIBLE
                    gameList.visibility = View.GONE
                    gameError.visibility = View.GONE
                } else {
                    gameLoading.visibility = View.GONE
                }
            }
        })
    }

}