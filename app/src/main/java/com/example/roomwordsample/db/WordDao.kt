package com.example.roomwordsample.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomwordsample.db.repository.Word

@Dao
interface WordDao {
    @Query("SELECT * FROM word_table ORDER BY word")
    fun getAllWords(): List<Word>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun addNewWord(newWord: Word)

   @Query("DELETE FROM word_table")
   suspend fun deleteAll()




}