package com.example.diplom

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.diplom.data.UserRepository
import com.example.diplom.viewmodel.RegistrationViewModel
import java.security.MessageDigest
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource


fun hashPassword(password: String): String {
    return MessageDigest.getInstance("SHA-256")
        .digest(password.toByteArray())
        .joinToString("") { "%02x".format(it) }
}

// ViewModel для управления логикой регистрации


@Composable
fun RegistrationScreen(
    onBackClick: () -> Unit,
    userRepository: UserRepository, // Передаем UserRepository в composable
    viewModel: RegistrationViewModel = viewModel(factory = RegistrationViewModelFactory(userRepository))

) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Регистрация",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Поле имени пользователя
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Имя пользователя") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Электронная почта") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))
        // Поле пароля
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    val icon = if (passwordVisible) {
                        painterResource(id = R.drawable.eye) // Используйте ваш ресурс для открытого глаза
                    } else {
                        painterResource(id = R.drawable.closed_eye) // Используйте ваш ресурс для закрытого глаза
                    }
                    Image(painter = icon, contentDescription = if (passwordVisible) "Скрыть" else "Показать", modifier = Modifier.clickable { passwordVisible = !passwordVisible })
                }
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка регистрации
        Button(
            onClick = {
                if (username.isNotBlank() && password.isNotBlank() && email.isNotBlank()) {
                    viewModel.registerUser(username, email, password)
                    onBackClick()
                    Toast.makeText(context, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Зарегистрироваться")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка назад
        Text(
            text = "Назад",
            color = Color.Blue,
            modifier = Modifier.clickable { onBackClick() }
        )
    }
}


