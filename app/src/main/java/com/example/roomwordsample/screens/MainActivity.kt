package com.example.roomwordsample.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomwordsample.adapter.DeletedClicked
import com.example.roomwordsample.adapter.WordDetailsClick
import com.example.roomwordsample.adapter.WordListAdapter
import com.example.roomwordsample.databinding.ActivityMainBinding
import com.example.roomwordsample.db.WordViewModel
import com.example.roomwordsample.db.repository.Word

class MainActivity : AppCompatActivity(), WordDetailsClick, DeletedClicked {
   private lateinit var wordViewModel: WordViewModel

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
       wordViewModel = ViewModelProvider(this, ViewModelProvider
           .AndroidViewModelFactory.getInstance(application)
       ).get(WordViewModel::class.java)

        wordViewModel.allWords.observe(this, {list->
            // Update the cached copy of the words in the adapter.
            list?.let {
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

