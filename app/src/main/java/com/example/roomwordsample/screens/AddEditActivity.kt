package com.example.roomwordsample.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.roomwordsample.R
import com.example.roomwordsample.WordApplications
import com.example.roomwordsample.databinding.ActivityAddEditBinding
import com.example.roomwordsample.db.WordViewModel
import com.example.roomwordsample.db.WordViewModelFactory
import com.example.roomwordsample.db.repository.Word
import java.text.SimpleDateFormat
import java.util.*

class AddEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEditBinding

   private val wordViewModel: WordViewModel by viewModels {
       WordViewModelFactory((application as WordApplications).repository)
   }
    private var wordId = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // wordViewModel = ViewModelProvider(this,
          //  ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)).get(wordViewModel::class.java)

        val wordType = intent.getStringExtra("wordType")
        if (wordType.equals("Edit")) {
            val wordTitle = intent.getStringExtra("wordTitle")
            val wordDesc = intent.getStringExtra("wordDesc")
             wordId = intent.getIntExtra("wordId", -1)
            binding.saveBtn.text = getString(R.string.update)
            binding.wordInEt.setText(wordTitle)
            binding.meaningInEt.setText(wordDesc)
        } else {
            binding.saveBtn.text = getString(R.string.save)
        }

        binding.saveBtn.setOnClickListener {
            val wordTitle = binding.wordInEt.text.toString()
            val wordDesc = binding.meaningInEt.text.toString()
            val pattern = "dd MMM, yyyy - HH:mm"

            if (wordType.equals("Edit")) {
                if (wordTitle.isNotEmpty() && wordDesc.isNotEmpty()) {

                    val date = SimpleDateFormat(pattern, Locale.getDefault())
                    val currentDate = date.format(Date())
                    val updateWord = Word(wordTitle, wordDesc, currentDate)
                    updateWord.id = wordId.toLong()
                    wordViewModel.updateWord(updateWord)
                    Toast.makeText(this, "Word updated...", Toast.LENGTH_LONG).show()
                }
            }else{
                    if (wordTitle.isNotEmpty() && wordDesc.isNotEmpty()) {
                        val date = SimpleDateFormat(pattern, Locale.getDefault())
                        val currentDate = date.format(Date())
                        wordViewModel.insert(Word(wordTitle, wordDesc, currentDate))
                        Toast.makeText(this, "Word Added...", Toast.LENGTH_LONG).show()
                    }
                }
                startActivity(Intent(applicationContext, MainActivity::class.java))
                this.finish()
            }
        }
    }


