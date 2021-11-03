package com.example.latihanhangman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val mFragmentManager = supportFragmentManager
        val mfSatu = homeScreen()

        mFragmentManager.findFragmentByTag(homeScreen::class.java.simpleName)
        mFragmentManager
            .beginTransaction()
            .add(R.id.frameContainer, mfSatu, homeScreen::class.java.simpleName)
            .commit()
    }
}