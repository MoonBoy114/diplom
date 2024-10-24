package compose.project.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch


@Composable
fun AppGridContent() {
    val drawerState = rememberDrawerState(DrawerValue.Closed) // Состояние для Drawer
    val coroutineScope = rememberCoroutineScope()

    // Окружение для скрытого меню
    ModalDrawer(
        drawerState = drawerState,
        gesturesEnabled = true, // Отключаем жесты для открытия и закрытия меню
        drawerContent = {
            DrawerMenuContent() // Содержимое бокового меню
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("StandPack") },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    coroutineScope.launch {
                                        drawerState.open() // Открываем боковое меню при клике на иконку
                                    }
                                }
                            ) {
                                Icon(Icons.Filled.Menu, contentDescription = "Menu")
                            }
                        }
                    )
                },
                content = {
                    // Используем Box для создания фона с возможностью клика
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable(
                                onClick = {
                                    coroutineScope.launch {
                                        if (drawerState.isOpen) {
                                            drawerState.close() // Закрываем меню при клике на фон
                                        }
                                    }
                                },
                                indication = null, // Убираем эффект нажатия
                                interactionSource = remember { MutableInteractionSource() } // Создаем источник взаимодействия
                            )
                    ) {
                        MainScreenContent() // Выводим текст на главной
                    }
                }
            )
        }
    )
}


@Composable
fun MainScreenContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center  // Центрируем содержимое по середине
    ) {
        Text(
            text = "Выберите нужное меню",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun DrawerMenuContent() {
    Box(
        modifier = Modifier
            .wrapContentWidth()  // Меню занимает только необходимое место для содержимого
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column(
            Modifier
                .wrapContentWidth()
                .wrapContentHeight()  // Меню занимает только необходимую высоту
        ) {
            Text(text = "Меню", fontSize = 24.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            MenuItem(text = "Заметки")
            MenuItem(text = "Калькулятор")
            MenuItem(text = "Переводчик")
            MenuItem(text = "Часы")
            MenuItem(text = "Настройки")
            MenuItem(text = "Привет")
        }
    }
}

@Composable
fun MenuItem(text: String) {
    Row(
        Modifier
            .clickable { /* Действие для пункта меню */ }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, fontSize = 18.sp)
    }
}