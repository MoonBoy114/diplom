package compose.project.demo

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import org.jetbrains.compose.resources.painterResource
import composedemo.composeapp.generated.resources.Res
import composedemo.composeapp.generated.resources.compose_multiplatform


@Composable
fun AppGridContent(
) {
    MaterialTheme {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val options = listOf("Заметки", "Калькулятор", "Переводчик", "Часы")
            val gridSize = 2
            GridDesktop(options, gridSize)
        }
    }
}

@Composable
fun GridDesktop(options: List<String>, gridSize: Int) {
    val chunkedOptions = options.chunked(gridSize)
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()

    ) {
        for (row in chunkedOptions) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .heightIn(min = 100.dp, max = 200.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (option in row) {
                    GridItemDesktop(option, Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun GridItemDesktop(option: String, modifier: Modifier) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = modifier.fillMaxSize()
            // Вместо fillMaxHeight используем aspectRatio для сохранения пропорций

            .clickable { /* Add navigation or action for each item */ }
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
                painter = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)  // Ограничиваем размер изображения
                    .padding(bottom = 8.dp),  // Добавляем отступ под изображением
                contentScale = ContentScale.Fit  // Сохраняем пропорции изображения
            )
            Text(
                text = option,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}