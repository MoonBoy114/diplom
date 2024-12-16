package com.example.diplom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.diplom.data.UserRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isLog = remember { mutableStateOf(false) }
            val isRegistering = remember { mutableStateOf(false) }
            val errorMessage = remember { mutableStateOf<String?>(null) }

            // Получаем экземпляр базы данных
            val db = AppDatabase.getInstance(this)

            // Получаем экземпляр UserDao
            val userDao = db.userDao()

            // Получаем экземпляр UserRepository
            val userRepository = UserRepository.getInstance(userDao)

            Crossfade(
                targetState = when {
                    isLog.value -> "LoggedIn" // Use isLoggedIn state
                    isRegistering.value -> "Registering"
                    else -> "Login"
                }, label = "animation"
            ) { screen ->
                when (screen) {
                    "LoggedIn" -> AppGrid()
                    "Login" -> LoginScreenAndroid(
                        onLoginSuccess = { isLog.value = true }, // Update isLoggedIn on success
                        onRegisterClick = { isRegistering.value = true },
                        userRepository = userRepository
                    )

                    "Registering" -> RegistrationScreen(
                        onBackClick = { isRegistering.value = false },
                        userRepository = userRepository
                    )
                }
            }
        }
    }
}