package com.sahanmondal.mvvm_firebase_authentication.ui.auth.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sahanmondal.mvvm_firebase_authentication.R
import com.sahanmondal.mvvm_firebase_authentication.others.EventObserver
import com.sahanmondal.mvvm_firebase_authentication.others.snackBar
import com.sahanmondal.mvvm_firebase_authentication.ui.auth.AuthViewModel
import com.sahanmondal.mvvm_firebase_authentication.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var viewModel: AuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        subscribeToObservers()

        btnLogin.setOnClickListener {
            viewModel.login(
                etEmail.text.toString().trim(),
                etPassword.text.toString().trim()
            )
        }

        btnGoToRegister.setOnClickListener {
            if (findNavController().previousBackStackEntry != null) {
                findNavController().popBackStack()
            } else {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                )
            }
        }
    }

    private fun subscribeToObservers() {
        viewModel.loginStatus.observe(viewLifecycleOwner, EventObserver(
            onError = {
                loginProgressBar.isVisible = false
                snackBar(it)
            },
            onLoading = {
                loginProgressBar.isVisible = true
            }
        ) {
            loginProgressBar.isVisible = false
            Intent(requireContext(), MainActivity::class.java).also {
                startActivity(it)
                requireActivity().finish()
            }
        })
    }
}