package com.traineeship.placeofgames.view.places

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.placeofgames.R
import com.traineeship.placeofgames.viewmodels.PlacesViewModel

class PlacesFragment : Fragment(), PlacesAdapter.PlaceClickListener {

    private lateinit var rvPlaces: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private val placesViewModel: PlacesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_places, container, false)
        initViews(view)

        rvPlaces.layoutManager = LinearLayoutManager(this.context)
        rvPlaces.adapter = PlacesAdapter(mutableListOf(), this)
        getPlaces()

        swipeRefresh.setOnRefreshListener {
            placesViewModel.loadPlaces()
        }

        return view
    }

    private fun getPlaces(){
        placesViewModel.getPlaces().observe(viewLifecycleOwner) {
            rvPlaces.adapter = PlacesAdapter(it, this)
            swipeRefresh.isRefreshing = false
        }
    }

    private fun initViews(view: View){
        rvPlaces = view.findViewById(R.id.rv_places)
        swipeRefresh = view.findViewById(R.id.swipe_refresh_places)
    }

    override fun onClickItem(view: View, placeId: Int) {

    }
}