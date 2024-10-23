package compose.project.demo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun LoginScreen(
    onLoginClick: (String, String) -> Unit,
    errorMessage: String? = null
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        // Placeholder for a logo or icon

        // Username field
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Имя пользователя", style = MaterialTheme.typography.body1) },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)) // Закругление углов
                .padding(vertical = 8.dp, horizontal = 16.dp), // Добавление отступов
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                textColor = MaterialTheme.colors.onSurface, // Цвет текста
                focusedIndicatorColor = MaterialTheme.colors.primary, // Цвет индикатора при фокусе
                unfocusedIndicatorColor = MaterialTheme.colors.onSurface.copy(alpha = 0.5f), // Цвет индикатора без фокуса
                placeholderColor = MaterialTheme.colors.onSurface.copy(alpha = 0.5f) // Цвет текста подсказки
            ),
            shape = RoundedCornerShape(10.dp), // Закругление углов
            singleLine = true, // Однострочный ввод
            textStyle = LocalTextStyle.current.copy(fontSize = 18.sp, lineHeight = 24.sp) // Увеличиваем размер текста
        )


        Spacer(modifier = Modifier.height(16.dp))



        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль", style = MaterialTheme.typography.body1) },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)) // Закругление углов
                .padding(vertical = 8.dp, horizontal = 16.dp), // Добавление отступов
            visualTransformation = PasswordVisualTransformation(), // Скрытие символов пароля
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                textColor = MaterialTheme.colors.onSurface, // Цвет текста
                focusedIndicatorColor = MaterialTheme.colors.primary, // Цвет индикатора при фокусе
                unfocusedIndicatorColor = MaterialTheme.colors.onSurface.copy(alpha = 0.5f), // Цвет индикатора без фокуса
                placeholderColor = MaterialTheme.colors.onSurface.copy(alpha = 0.5f) // Цвет текста подсказки
            ),
            shape = RoundedCornerShape(10.dp), // Закругление углов
            singleLine = true, // Однострочный ввод
            textStyle = LocalTextStyle.current.copy(fontSize = 18.sp, lineHeight = 24.sp) // Увеличиваем размер текста
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Login button with rounded corners
        Button(
            onClick = { onLoginClick(username, password) },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary
            )
        ) {
            Text("Вход", style = MaterialTheme.typography.button)
        }

        // Error message display
        errorMessage?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = it,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}