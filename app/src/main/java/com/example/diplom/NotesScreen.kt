package com.example.diplom

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.diplom.data.Note
import com.example.diplom.viewmodel.NoteViewModel

@Composable
fun NotesScreen(viewModel: NoteViewModel) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    val notes by viewModel.notes.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Заголовок
        Text(text = "Заметки", style = MaterialTheme.typography.headlineMedium)

        // Поля для добавления заметки
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Заголовок") },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )
        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Содержимое") },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )
        Button(
            onClick = {
                viewModel.addNote(title, content)
                title = ""
                content = ""
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Добавить заметку")
        }

        // Список заметок
        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(notes) { note ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = note.title, style = MaterialTheme.typography.titleMedium)
                        Text(text = note.content, style = MaterialTheme.typography.bodyMedium)
                        Button(
                            onClick = { viewModel.deleteNote(note) },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Text("Удалить")
                        }
                    }
                }
            }
        }
    }
}