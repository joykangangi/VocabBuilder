package com.example.roomwordsample.db

import androidx.lifecycle.*
import com.example.roomwordsample.db.repository.Word
import com.example.roomwordsample.db.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class WordViewModel(private val repository: WordRepository): ViewModel() {
    val allWords: LiveData<List<Word>> = repository.allWords

    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }

    fun deleteWord(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(word)
    }

    fun updateWord(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(word)
    }
}

class WordViewModelFactory(private val repository: WordRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(WordRepository::class.java)
            .newInstance(repository)

    }
}