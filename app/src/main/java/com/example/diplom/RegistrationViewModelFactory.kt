package com.example.diplom

import androidx.lifecycle.ViewModel
import com.example.diplom.data.UserRepository
import androidx.lifecycle.ViewModelProvider
import com.example.diplom.viewmodel.RegistrationViewModel

class RegistrationViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegistrationViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}