package com.example.diplom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.diplom.data.NoteRepository
import com.example.diplom.data.UserRepository
import com.example.diplom.viewmodel.NoteViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val isLog = remember { mutableStateOf(false) }
            val isRegistering = remember { mutableStateOf(false) }
            val errorMessage = remember { mutableStateOf<String?>(null) }


            val db = AppDatabase.getInstance(this)


            val userDao = db.userDao()
            val userRepository = UserRepository.getInstance(userDao)


            val noteDao = db.noteDao()
            val noteRepository = NoteRepository(noteDao)
            val noteViewModel = NoteViewModelFactory(noteRepository).create(NoteViewModel::class.java)


            val navController: NavHostController = rememberNavController()

            NavHost(navController = navController, startDestination = "crossfade") {
                // Внешняя навигация с Crossfade
                composable("crossfade") {
                    Crossfade(
                        targetState = when {
                            isLog.value -> "LoggedIn"
                            isRegistering.value -> "Registering"
                            else -> "Login"
                        }
                    ) { screen ->
                        when (screen) {
                            "LoggedIn" -> AppGrid(onItemClick = { option ->
                                if (option == "Заметки") {
                                    navController.navigate("notesScreen")
                                }
                            })
                            "Login" -> LoginScreenAndroid(
                                onLoginSuccess = { isLog.value = true },
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

                composable("notesScreen") {
                    NotesScreen(viewModel = noteViewModel)
                }
            }
        }
    }
}