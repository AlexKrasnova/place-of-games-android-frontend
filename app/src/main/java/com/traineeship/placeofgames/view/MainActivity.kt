package com.traineeship.placeofgames.view

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.traineeship.placeofgames.R
import com.traineeship.placeofgames.utils.TokenUtil

class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var tokenUtil: TokenUtil
    private var isLogin = false
    private var isPressedBack = false


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
            navController.navigate(R.id.containerContent)
        } else {
            navController.navigate(R.id.containerAuth)
        }
    }

    override fun onBackPressed() {
        if (isLogin) {

            val contentNavController = findNavController(R.id.nav_host_fragment_content)
            val isUp = contentNavController.navigateUp()
            if (!isUp){
                if (isPressedBack) {
                    super.onBackPressed()
                }
                isPressedBack = true
                Toast.makeText(this, "Нажмите ещё раз для выхода", Toast.LENGTH_SHORT).show()

                Handler(Looper.getMainLooper()).postDelayed(Runnable { isPressedBack = false }, 2000)
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
}
