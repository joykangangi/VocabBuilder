package com.example.roomwordsample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwordsample.R
import com.example.roomwordsample.databinding.WordListItemBinding
import com.example.roomwordsample.db.repository.Word

class WordListAdapter(
    val context: Context,
    val deletedClicked: DeletedClicked,
    val detailsClick: WordDetailsClick
    ): ListAdapter<Word,WordListAdapter.WordViewHolder>(WordDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        val binding = WordListItemBinding.inflate(layoutInflater, parent, false)
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val currentWord = getItem(position)
        holder.bind(currentWord)
    }

    inner class WordViewHolder(private var binding: WordListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentWord: Word) {
            binding.textView.text = currentWord.wordTitle
            binding.timeStamp.text = context.getString(R.string.las_up, currentWord.timeStamp)
            binding.root.setOnClickListener {
                detailsClick.onDetailClick(currentWord)
            }

            binding.deleteBtn.setOnClickListener {
                deletedClicked.onDeleteIconClick(currentWord)
            }

        }
    }

    object WordDiffUtil : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }

    }
}

interface DeletedClicked {
    fun onDeleteIconClick(word: Word)
}

interface WordDetailsClick {
    fun onDetailClick(word: Word)
}
