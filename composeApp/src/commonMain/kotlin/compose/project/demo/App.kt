package compose.project.demo



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composedemo.composeapp.generated.resources.Res
import composedemo.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AppGrid() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Основной контент с прокручиваемым списком
            LazyColumn(
                modifier = Modifier
                    .weight(1f)  // Позволяет LazyColumn занимать всё доступное пространство
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)  // Добавляем расстояние между строками
            ) {
                val options = listOf("Заметки", "Калькулятор", "Переводчик", "Часы", "Камера", "Приветствие")
                val gridSize = 2
                items(options.chunked(gridSize)) { rowOptions ->
                    Row(
                        Modifier
                            .fillMaxHeight(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),  // Добавляем расстояние между колонками
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        for (option in rowOptions) {
                            GridItem(option, Modifier.weight(1f))  // Задаем фиксированный размер для карточек
                        }
                    }
                }
            }

            // Нижняя навигационная панель
            BottomNavigationBar()
        }
    }
}


@Composable
fun GridItem(option: String, modifier: Modifier) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = modifier
            .size(300.dp)  // Устанавливаем фиксированный размер для карточек
            .clickable { /* действие */ }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painterResource(Res.drawable.compose_multiplatform),
                contentDescription = null,
                modifier = Modifier.size(80.dp),  // Размер изображения
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = option,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}
@Composable
fun BottomNavigationBar() {
    BottomNavigation(
        backgroundColor = Color.Gray,
        contentColor = Color.White
    ) {
        BottomNavigationItem(
            icon = { Icon(painterResource(Res.drawable.compose_multiplatform), contentDescription = null, modifier = Modifier.size(24.dp)) },
            label = { Text("Настройки") },
            selected = false,
            onClick = {  },
            modifier = Modifier.weight(1f)
        )
        BottomNavigationItem(
            icon = { Icon(painterResource(Res.drawable.compose_multiplatform), contentDescription = null, modifier = Modifier.size(24.dp)) },
            label = { Text("Профиль") },
            selected = false,
            onClick = {  },
            modifier = Modifier.weight(1f)
        )
        BottomNavigationItem(
            icon = { Icon(painterResource(Res.drawable.compose_multiplatform), contentDescription = null, modifier = Modifier.size(24.dp)) },
            label = { Text("О приложении") },
            selected = false,
            onClick = {  },
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview
@Composable
fun PreviewAppGrid() {
    AppGrid()
}