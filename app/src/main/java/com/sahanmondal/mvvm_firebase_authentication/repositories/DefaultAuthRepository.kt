package com.sahanmondal.mvvm_firebase_authentication.repositories

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sahanmondal.mvvm_firebase_authentication.data.entities.User
import com.sahanmondal.mvvm_firebase_authentication.others.Resource
import com.sahanmondal.mvvm_firebase_authentication.others.safeCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class DefaultAuthRepository : AuthRepository {

    val auth = FirebaseAuth.getInstance()
    private val usersRef = FirebaseFirestore.getInstance().collection("users")

    override suspend fun register(
        email: String,
        name: String,
        password: String
    ): Resource<AuthResult> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                val uid = result.user?.uid!!
                val user = User(uid = uid, name = name)
                usersRef.document(uid).set(user).await()
                Resource.Success(result)
            }
        }
    }

    override suspend fun login(email: String, password: String): Resource<AuthResult> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                Resource.Success(result)
            }
        }
    }
}