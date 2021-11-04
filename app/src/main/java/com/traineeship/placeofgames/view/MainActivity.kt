package com.traineeship.placeofgames.view

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.traineeship.placeofgames.R
import com.traineeship.placeofgames.utils.TokenUtil

class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var tokenUtil: TokenUtil
    private var isLogin = false


    override fun onStart() {
        super.onStart()

        tokenUtil.preferences
            .registerOnSharedPreferenceChangeListener(this);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        navController = navHostFragment.navController

        tokenUtil = TokenUtil(this)
        isLogin = tokenUtil.token != null

        updateUiIfLogin()
    }

    private fun initViews() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
    }

    private fun updateUiIfLogin(){
        if (isLogin){
            navController.navigate(R.id.action_containerAuth_to_containerContent)
        } else {
            navController.navigate(R.id.action_containerContent_to_containerAuth)
        }
    }

    override fun onBackPressed() {
        if (isLogin) {

            val loginNavController = findNavController(R.id.nav_host_fragment_content)
            val isUp = loginNavController.navigateUp()
            if (!isUp){
                super.onBackPressed()
            }
        } else {
            navController.navigateUp()
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {

        Log.d("Main", "onSharedPreferenceChanged: " + tokenUtil.token)
        isLogin = tokenUtil.token != null
        updateUiIfLogin()
    }

    interface IOnBackPressed {
        fun onBackPressed(): Boolean
    }
}
