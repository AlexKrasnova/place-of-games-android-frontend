package com.traineeship.placeofgames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.placeofgames.R
import com.traineeship.placeofgames.data.Event
import com.traineeship.placeofgames.data.Place
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : BaseActivity() {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        initViews()

        navController = navHostFragment.navController
        bottomNav.setupWithNavController(navController)
        NavigationUI.setupWithNavController(bottomNav, navController)

        bottomNav.setOnItemReselectedListener {
            when(it.itemId){
                R.id.games -> navController.navigateUp()
            }
        }
    }

    private fun initViews(){
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        bottomNav = findViewById(R.id.bottom_nav)
    }
}
