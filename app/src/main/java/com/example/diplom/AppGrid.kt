package com.example.diplom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.res.painterResource

@Composable
fun AppGrid(onItemClick: (String) -> Unit) {
    val optionImages = mapOf(
        "Заметки" to R.drawable.notes,
        "Калькулятор" to R.drawable.calculator,
        "Переводчик" to R.drawable.translate,
        "Погода" to R.drawable.weather,
        "Камера" to R.drawable.camera,
        "Музыка" to R.drawable.music
    )

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f).
                    padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val options = optionImages.keys.toList()
                val gridSize = 2

                // Разбиваем список опций на строки по два элемента
                items(options.chunked(gridSize)) { rowOptions ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Отображаем по два элемента в строку
                        for (option in rowOptions) {
                            val imageResource = optionImages.getValue(option)
                            GridItem(
                                option = option,
                                imageResource = imageResource,
                                onItemClick = onItemClick,
                                modifier = Modifier.weight(1f) // Равномерное распределение
                            )
                        }
//                        // Добавляем пустой Spacer для выравнивания последней строки
//                        if (rowOptions.size < gridSize) {
//                            Spacer(modifier = Modifier.weight(1f))
//                        }
                    }
                }

            }
            BottomNavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp) // Высота навигационной панели
            )

        }

    }
}

@Composable
fun GridItem(option: String, imageResource: Int, onItemClick: (String) -> Unit, modifier: Modifier) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier
            .aspectRatio(1f)
            .clickable { onItemClick(option) }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = option,
                modifier = Modifier.size(70.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = option,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
    }
}

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = Color.Gray,
        contentColor = Color.White,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Image(
                    painter = painterResource(R.drawable.settin),
                    contentDescription = "Настройки",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Настройки", fontSize = 12.sp, color = Color.White) },
            selected = false,
            onClick = { /* Действие для настроек */ }
        )

        NavigationBarItem(
            icon = {
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "Профиль",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Профиль", fontSize = 12.sp, color = Color.White) },
            selected = false,
            onClick = { /* Действие для профиля */ }
        )

        NavigationBarItem(
            icon = {
                Image(
                    painter = painterResource(R.drawable.about),
                    contentDescription = "О приложении",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("О приложении", fontSize = 12.sp, color = Color.White) },
            selected = false,
            onClick = { /* Действие для информации */ }
        )
    }
}