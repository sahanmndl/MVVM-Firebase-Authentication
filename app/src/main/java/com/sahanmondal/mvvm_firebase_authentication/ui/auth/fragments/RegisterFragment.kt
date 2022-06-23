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
import kotlinx.android.synthetic.main.fragment_register.*

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var viewModel: AuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        subscribeToObservers()

        btnRegister.setOnClickListener {
            viewModel.register(
                etEmail.text.toString().trim(),
                etName.text.toString().trim(),
                etPassword.text.toString().trim()
            )
        }

        btnGoToLogin.setOnClickListener {
            if (findNavController().previousBackStackEntry != null) {
                findNavController().popBackStack()
            } else {
                findNavController().navigate(
                    RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                )
            }
        }
    }

    private fun subscribeToObservers() {
        viewModel.registerStatus.observe(viewLifecycleOwner, EventObserver(
            onError = {
                registerProgressBar.isVisible = false
                btnRegister.isEnabled = true
                snackBar(it)
            },
            onLoading = {
                registerProgressBar.isVisible = true
                btnRegister.isEnabled = false
            }
        ) {
            registerProgressBar.isVisible = false
            btnRegister.isEnabled = true
            Intent(requireContext(), MainActivity::class.java).also {
                startActivity(it)
                requireActivity().finish()
            }
        })
    }
}