package com.example.dictionarylist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionarylist.databinding.ActivityWordsLayoutBinding

class WordsActivity : AppCompatActivity() {
    lateinit var wordList: ArrayList<Words>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityWordsLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        wordList = ArrayList()
        addWords()

        binding.wordsList.layoutManager = LinearLayoutManager(this)
        binding.wordsList.addItemDecoration(DividerItemDecoration(this, 1))
        binding.wordsList.adapter = WordAdapter(wordList)
    }

    fun addWords() {
        wordList.add(Words("一", "yī", "num./adj. one, 1; same; whole; whole-hearted"))
    }
}