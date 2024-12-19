package com.example.diplom.data

class NoteRepository(private val noteDao: NoteDao) {
    suspend fun getAllNotes() = noteDao.getAllNotes()
    suspend fun insertNote(note: Note) = noteDao.insertNote(note)
    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)
    suspend fun updateNote(note: Note) = noteDao.updateNote(note)
}