package com.example.roomwordsample.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomwordsample.db.repository.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM word_table ORDER BY id ")
    fun getAllWords(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun addNewWord(newWord: Word)

   @Delete
   suspend fun delete(newWord: Word)

   @Update
   suspend fun update(newWord: Word)




}