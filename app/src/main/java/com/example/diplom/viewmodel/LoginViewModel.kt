package com.example.diplom.viewmodel

import androidx.activity.result.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplom.data.UserRepository
import com.example.diplom.hashPassword
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    suspend fun loginUser(username: String, password: String, onLoginSuccess: () -> Unit, onLoginFailure: (String) -> Unit) {
        val user = userRepository.getUserByUsername(username)
        if (user != null) {
            onLoginSuccess()
        } else {
            onLoginFailure("Неверный логин или пароль")
        }
    }
}