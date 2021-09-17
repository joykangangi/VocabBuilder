package com.example.roomwordsample.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomwordsample.WordApplications
import com.example.roomwordsample.adapter.DeletedClicked
import com.example.roomwordsample.adapter.WordDetailsClick
import com.example.roomwordsample.adapter.WordListAdapter
import com.example.roomwordsample.databinding.ActivityMainBinding
import com.example.roomwordsample.db.WordViewModel
import com.example.roomwordsample.db.WordViewModelFactory
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
            val intent = Intent(applicationContext, NewWordActivity::class.java)
            startActivity(intent)
        }
        wordViewModel.allWords.observe(this, {
            // Update the cached copy of the words in the adapter.
            it?.let { myAdapter.submitList(it) }
        })
    }

    override fun onDetailClick(word: Word) {
        TODO("Not yet implemented")
    }

    override fun onDeleteIconClick(word: Word) {
        TODO("Not yet implemented")
    }

}

