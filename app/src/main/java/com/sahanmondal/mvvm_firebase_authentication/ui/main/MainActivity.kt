package com.sahanmondal.mvvm_firebase_authentication.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sahanmondal.mvvm_firebase_authentication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}