package com.traineeship.placeofgames

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.placeofgames.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.traineeship.placeofgames.utils.TokenUtil

class MainActivity : BaseActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var tokenUtil: TokenUtil
    private var isLogin = false


    override fun onStart() {
        super.onStart()


        tokenUtil.preferences
            .registerOnSharedPreferenceChangeListener(this);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        initViews()

        navController = navHostFragment.navController

        tokenUtil = TokenUtil(this)
        isLogin = tokenUtil.token != null
        updateUiIfLogin()

        bottomNav.setupWithNavController(navController)

        bottomNav.setOnItemReselectedListener {
            when (it.itemId) {
                R.id.games -> navController.navigateUp()
            }
        }
    }

    private fun initViews() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        bottomNav = findViewById(R.id.bottom_nav)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {

        Log.d("Main", "onSharedPreferenceChanged: " + tokenUtil.token)
        isLogin = tokenUtil.token != null
        updateUiIfLogin()
    }

    private fun updateUiIfLogin() {
        if (!isLogin) {
            val inflater = navController.navInflater
            val authGraph = inflater.inflate(R.navigation.nav_graph_auth)
            navController.graph = authGraph
            bottomNav.visibility = View.GONE
        } else {
            val inflater = navController.navInflater
            val authGraph = inflater.inflate(R.navigation.nav_graph)
            navController.graph = authGraph
            bottomNav.visibility = View.VISIBLE
        }
    }
}
