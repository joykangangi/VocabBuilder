package com.example.roomwordsample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomwordsample.db.repository.Word

@Database(
    entities = [Word::class],
    version = 1,
    exportSchema = false )
abstract class WordDatabase: RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        @Volatile private var instance: WordDatabase? = null
        private var LOCK = Any()

        // if the INSTANCE is not null, then return it,
        // if it is, then create the database
        operator fun invoke (context: Context) = instance?: synchronized(LOCK) {
            instance?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, WordDatabase::class.java, "word_db").build()
    }
}