package com.sahanmondal.mvvm_firebase_authentication.data.entities

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    val uid: String = "",
    val name: String = ""
)
