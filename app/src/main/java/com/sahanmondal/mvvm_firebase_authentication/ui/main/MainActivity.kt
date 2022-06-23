package com.sahanmondal.mvvm_firebase_authentication.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.sahanmondal.mvvm_firebase_authentication.R
import com.sahanmondal.mvvm_firebase_authentication.ui.auth.AuthActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Intent(this, AuthActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }
    }
}