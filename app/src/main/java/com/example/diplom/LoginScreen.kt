package com.example.diplom
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Typography
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenAndroid(
    onLoginClick: (String, String) -> Unit,
    onRegisterClick: () -> Unit,
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
        // Logo and title row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
        ) {
            Spacer(modifier = Modifier.width(12.dp))
            Image(
                painter = painterResource(R.drawable.logos), // Убедитесь, что logo.svg доступен в res/drawable как logo.xml или аналогичный формат
                contentDescription = "Logo",
                modifier = Modifier
                    .height(80.dp)  // Задаем размер логотипа
                    .clip(CircleShape)  // Делаем логотип круглым// Adjust height as needed
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "STANDPACK",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        // Username field
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Имя пользователя", style = MaterialTheme.typography.bodyMedium) },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .padding(vertical = 8.dp, horizontal = 16.dp),
            colors =  TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                disabledIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = androidx.wear.compose.material.ContentAlpha.disabled),
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = androidx.wear.compose.material.ContentAlpha.disabled)
            ),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(fontSize = 18.sp, lineHeight = 24.sp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password field
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль", style = MaterialTheme.typography.bodyMedium) },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
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
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                disabledIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = androidx.wear.compose.material.ContentAlpha.disabled),
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = androidx.wear.compose.material.ContentAlpha.disabled)

            ),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(fontSize = 18.sp, lineHeight = 24.sp)
        )

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = { onLoginClick(username, password) },
            modifier = Modifier
                .width(100.dp)
                .clip(RoundedCornerShape(8.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )
        ) {
            Text("Вход", style = MaterialTheme.typography.labelLarge, color = Color.White, fontSize = 20.sp)
        }


        errorMessage?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Вы еще не зарегистрированы? ",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Вперед регистрироваться!",
            style = MaterialTheme.typography.labelMedium.copy(color = Color.Blue),
            modifier = Modifier.clickable { onRegisterClick() }
        )



    }
}