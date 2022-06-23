package com.sahanmondal.mvvm_firebase_authentication.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.sahanmondal.mvvm_firebase_authentication.R
import com.sahanmondal.mvvm_firebase_authentication.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        if (FirebaseAuth.getInstance().currentUser != null) {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}