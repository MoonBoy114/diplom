package compose.project.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isLog = remember  { mutableStateOf(false)}
            val errorMessage = remember { mutableStateOf<String?>(null) }
            var username =
            if(isLog.value) {
                AppGrid()
            } else {
                LoginScreen(onLoginClick = {username, password ->
                    if(username == "admin" && password == "password") {
                        isLog.value = true
                    } else {
                        // Установка сообщения об ошибке
                        errorMessage.value = "Неверное имя пользователя или пароль"
                    }
                }, errorMessage.value)
            }
        }

    }
}
@Preview
@Composable
fun AppAndroidPreview() {
    AppGrid()
}