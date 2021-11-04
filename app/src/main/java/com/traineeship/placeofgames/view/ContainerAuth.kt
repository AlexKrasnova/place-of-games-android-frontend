package com.traineeship.placeofgames.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.traineeship.placeofgames.R

class ContainerAuth : Fragment() {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_auth_container, container, false)
//        navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment_auth) as NavHostFragment
//        navController = navHostFragment.navController

        return view
    }
}