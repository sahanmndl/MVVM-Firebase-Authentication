package com.sahanmondal.mvvm_firebase_authentication.ui.auth

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.sahanmondal.mvvm_firebase_authentication.R
import com.sahanmondal.mvvm_firebase_authentication.others.Event
import com.sahanmondal.mvvm_firebase_authentication.others.Resource
import com.sahanmondal.mvvm_firebase_authentication.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val applicationContext: Context,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _registerStatus = MutableLiveData<Event<Resource<AuthResult>>>()
    val registerStatus: LiveData<Event<Resource<AuthResult>>> = _registerStatus

    private val _loginStatus = MutableLiveData<Event<Resource<AuthResult>>>()
    val loginStatus: LiveData<Event<Resource<AuthResult>>> = _loginStatus

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            val error = applicationContext.getString(R.string.error_input_empty)
            _loginStatus.postValue(Event(Resource.Error(error)))
        } else {
            _loginStatus.postValue(Event(Resource.Loading()))
            viewModelScope.launch(dispatcher) {
                val result = repository.login(email, password)
                _loginStatus.postValue(Event(result))
            }
        }
    }

    fun register(email: String, name: String, password: String) {
        val error =
            if (email.isEmpty() || name.isEmpty() || password.isEmpty()) {
                applicationContext.getString(R.string.error_input_empty)
            } else if (password.length < 8) {
                applicationContext.getString(R.string.error_password_short)
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                applicationContext.getString(R.string.error_invalid_email)
            } else null

        error?.let {
            _registerStatus.postValue(Event(Resource.Error(it)))
            return
        }

        _registerStatus.postValue(Event(Resource.Loading()))
        viewModelScope.launch(dispatcher) {
            val result = repository.register(email, name, password)
            _registerStatus.postValue(Event(result))
        }
    }
}