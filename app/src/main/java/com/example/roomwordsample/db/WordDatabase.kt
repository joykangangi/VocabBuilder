package com.example.roomwordsample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomwordsample.db.repository.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [Word::class],
    version = 1,
    exportSchema = false )
abstract class WordDatabase: RoomDatabase() {
    abstract fun wordDao(): WordDao


    /*private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
          instance?.let { database ->
                scope.launch {
                    val wordDao = database.wordDao()

                    // Delete all content here.
                    wordDao.deleteAll()

                    // Add sample words.
                    var word = Word("Subverted")
                    wordDao.addNewWord(word)
                    word = Word("Sedition")
                    wordDao.addNewWord(word)
                }
            }
        }
    }
*/

    companion object {
        @Volatile private var instance: WordDatabase? = null
        private var LOCK = Any()

        // if the INSTANCE is not null, then return it,
        // if it is, then create the database
        operator fun invoke (context: Context) = instance?: synchronized(LOCK) {
            instance?: createDatabase(context).also { instance = it }
            //instance = if (instance == null) createDatabase(context) else instance
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, WordDatabase::class.java, "word_db")
                //.addCallback(WordDatabaseCallback(scope))
                .build()
    }
}