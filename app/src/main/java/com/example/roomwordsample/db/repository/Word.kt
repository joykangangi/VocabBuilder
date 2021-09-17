package com.example.roomwordsample.db.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word(
    @ColumnInfo val word: String,
    @ColumnInfo val description: String,
    @ColumnInfo val timeStamp: String
    )
{
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
}