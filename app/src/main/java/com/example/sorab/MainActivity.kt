package com.example.sorab

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_Sorab)

        var i = 0
        while (i < 1000000000) {
            i++
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val actionBar: android.app.ActionBar? = actionBar
        //getActionBar()!!.setIcon(R.drawable.ic_logo_text_black_nonstop)
    }
}