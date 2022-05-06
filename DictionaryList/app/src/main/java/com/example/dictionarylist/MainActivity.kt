package com.example.dictionarylist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionarylist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnThemeItemClickListener {
    lateinit var themeList: ArrayList<Themes>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        themeList = ArrayList()
        addThemes()

        binding.themesList.layoutManager = LinearLayoutManager(this)
        binding.themesList.addItemDecoration(DividerItemDecoration(this, 1))
        binding.themesList.adapter = ThemeAdapter(themeList, this)
    }

    fun addThemes() {
        themeList.add(Themes("Nature", 50))
        themeList.add(Themes("Numbers", 50))
        themeList.add(Themes("HSK 1", 150))
        themeList.add(Themes("HSK 2", 150))
        themeList.add(Themes("HSK 3", 150))
        themeList.add(Themes("HSK 4", 150))
        themeList.add(Themes("HSK 5", 150))
        themeList.add(Themes("HSK 6", 150))
        themeList.add(Themes("HSK 7", 150))
    }

    override fun onItemClick(item: Themes, position: Int) {
        val intent = Intent(this, WordsActivity::class.java)
        startActivity(intent)
    }
}