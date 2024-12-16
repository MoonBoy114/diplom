package com.example.diplom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplom.data.User
import com.example.diplom.data.UserRepository
import com.example.diplom.hashPassword
import kotlinx.coroutines.launch

class RegistrationViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun registerUser(username: String,  email: String,password: String) {
        viewModelScope.launch {
            val hashedPassword = hashPassword(password)
            val user = User(username = username, email = email, passwordHash = hashedPassword)
            userRepository.registerUser(user)
        }
    }

}