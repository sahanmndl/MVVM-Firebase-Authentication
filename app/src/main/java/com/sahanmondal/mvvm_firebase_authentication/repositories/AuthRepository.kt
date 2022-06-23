package com.sahanmondal.mvvm_firebase_authentication.repositories

import com.google.firebase.auth.AuthResult
import com.sahanmondal.mvvm_firebase_authentication.others.Resource

interface AuthRepository {
    suspend fun register(email: String, name: String, password: String): Resource<AuthResult>
    suspend fun login(email: String, password: String): Resource<AuthResult>
}