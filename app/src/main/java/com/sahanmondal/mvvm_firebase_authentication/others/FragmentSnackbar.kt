package com.sahanmondal.mvvm_firebase_authentication.others

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.snackBar(text: String) {
    Snackbar.make(
        requireView(),
        text,
        Snackbar.LENGTH_LONG
    ).show()
}