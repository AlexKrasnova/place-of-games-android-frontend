package com.traineeship.placeofgames.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.traineeship.placeofgames.R
import com.traineeship.placeofgames.utils.setupWithNavController

class ContainerContent : Fragment(){

    private lateinit var bottomNav: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_content_container, container, false)
        initViews(view)
        setupNav()

        return view
    }

    private fun setupNav(){
        bottomNav.setupWithNavController(
            listOf(
                R.navigation.nav_graph_events,
                R.navigation.nav_graph_places,
                R.navigation.nav_graph_profile
            ), childFragmentManager, R.id.nav_host_fragment_content,
        ).observe(viewLifecycleOwner) {
            navController = it
        }
    }

    private fun initViews(view: View){
        bottomNav = view.findViewById(R.id.bottom_nav)
    }
}