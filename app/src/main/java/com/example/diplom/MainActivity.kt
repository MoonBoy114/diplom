package com.example.diplom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.diplom.data.UserRepository
import com.example.diplom.ui.theme.DiplomTheme

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

            Crossfade(targetState = when {
                isLog.value -> "LoggedIn"
                isRegistering.value -> "Registering"
                else -> "Login"
            }, label = "animation") { screen ->
                when (screen) {
                    "LoggedIn" -> AppGrid()
                    "Login" -> LoginScreenAndroid(
                        onLoginClick = { username, password ->
                            if (username == "admin" && password == "password") {
                                isLog.value = true
                            } else {
                                errorMessage.value = "Неверное имя пользователя или пароль"
                            }
                        },
                        onRegisterClick = { isRegistering.value = true },
                        errorMessage = errorMessage.value
                    )
                    "Registering" -> RegistrationScreen(
                        onBackClick = { isRegistering.value = false },
                        userRepository = userRepository // Передаем userRepository
                    )
                }
            }
        }
    }
}