package com.example.roomwordsample.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwordsample.R
import com.example.roomwordsample.adapter.WordListAdapter
import com.example.roomwordsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

            binding.wordRecyView.apply {
                adapter = WordListAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

        binding.fab.setOnClickListener {
            val intent = Intent(applicationContext, NewWordActivity::class.java)
            startActivity(intent)
        }


    }
}