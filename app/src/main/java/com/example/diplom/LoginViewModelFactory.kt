package com.example.diplom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.diplom.data.UserRepository
import com.example.diplom.viewmodel.LoginViewModel

class LoginViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}