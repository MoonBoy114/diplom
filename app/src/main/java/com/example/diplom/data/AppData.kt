package com.example.diplom.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val username: String,
    val email: String,
    val passwordHash: String,
    val registeredAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val content: String,
    val timestamp: Long
)

@Entity(tableName = "timers")
data class Timer(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val duration: Long,
    val isRunning: Boolean
)

@Entity(tableName = "calculations")
data class Calculation(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val expression: String,
    val result: String,
    val timestamp: Long
)

@Entity(tableName = "translations")
data class Translation(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val originalText: String,
    val translatedText: String,
    val languageFrom: String,
    val languageTo: String
)
