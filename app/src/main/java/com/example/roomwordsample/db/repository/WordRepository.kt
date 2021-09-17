package com.example.roomwordsample.db.repository

import androidx.lifecycle.LiveData
import com.example.roomwordsample.db.WordDao
import kotlinx.coroutines.flow.Flow


class WordRepository(private val wordDao: WordDao) {
    val allWords: LiveData<List<Word>> = wordDao.getAllWords()

    suspend fun insert(word: Word) {
        wordDao.addNewWord(word)
    }

    suspend fun delete(word: Word) {
        wordDao.delete(word)
    }

    suspend fun update(word: Word) {
        wordDao.update(word)
    }

}