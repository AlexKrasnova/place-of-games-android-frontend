package com.traineeship.placeofgames

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.progressindicator.LinearProgressIndicator

abstract class BaseActivity : AppCompatActivity(){
//    private lateinit var progressBar: LinearProgressIndicator

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
//        progressBar = findViewById(R.id.progress_bar)
    }

    open fun showProgressBar(visibility: Boolean) {
//        progressBar.visibility = if (visibility) View.VISIBLE else View.GONE
    }
}