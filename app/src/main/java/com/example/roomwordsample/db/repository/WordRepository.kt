package com.example.roomwordsample.db.repository

import com.example.roomwordsample.db.WordDao
import kotlinx.coroutines.flow.Flow


class WordRepository(private val wordDao: WordDao) {
    val allWords: Flow<List<Word>> = wordDao.getAllWords()

    suspend fun insert(word: Word) {
        wordDao.addNewWord(word)
    }

}