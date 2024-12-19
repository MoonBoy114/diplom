package com.example.diplom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.diplom.data.Calculation
import com.example.diplom.data.Note
import com.example.diplom.data.NoteDao
import com.example.diplom.data.Notes
import com.example.diplom.data.Timer
import com.example.diplom.data.Translation
import com.example.diplom.data.User
import com.example.diplom.data.UserDao

@Database(entities = [User::class, Note::class, Timer::class, Calculation::class, Translation::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
