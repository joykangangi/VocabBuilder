package com.example.roomwordsample.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.getInstance
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomwordsample.WordApplications
import com.example.roomwordsample.adapter.DeletedClicked
import com.example.roomwordsample.adapter.WordDetailsClick
import com.example.roomwordsample.adapter.WordListAdapter
import com.example.roomwordsample.databinding.ActivityMainBinding
import com.example.roomwordsample.db.WordDao
import com.example.roomwordsample.db.WordDatabase
import com.example.roomwordsample.db.WordViewModel
import com.example.roomwordsample.db.WordViewModelFactory
import com.example.roomwordsample.db.repository.Word
import com.example.roomwordsample.db.repository.WordRepository

class MainActivity : AppCompatActivity(), WordDetailsClick, DeletedClicked {
   //private lateinit var wordViewModel: WordViewModel
  //  private lateinit var repository: WordRepository
   private val wordViewModel: WordViewModel by viewModels {
       WordViewModelFactory((application as WordApplications).repository)
   }

       private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val myAdapter = WordListAdapter(this, this, this)
            binding.wordRecyView.apply {
                adapter = myAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

        binding.fab.setOnClickListener {
            val intent = Intent(this, AddEditActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        //repository = WordRepository(WordDatabase.invoke(this).wordDao())

       // wordViewModel = ViewModelProvider(this, WordViewModelFactory(repository)).get(WordViewModel::class.java)

        wordViewModel.allWords.observe(this, { words->
            // Update the cached copy of the words in the adapter.
            words.let {
                myAdapter.submitList(it)
            }
        })
    }

    override fun onDetailClick(word: Word) {
        val intent = Intent(this, AddEditActivity::class.java)
        intent.putExtra("wordType", "Edit" )
        intent.putExtra("wordTitle", word.wordTitle)
        intent.putExtra("wordDesc", word.description)
        intent.putExtra("wordId", word.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(word: Word) {
      wordViewModel.deleteWord(word)
        Toast.makeText(this, "${word.wordTitle} Deleted", Toast.LENGTH_LONG).show()
    }

}

