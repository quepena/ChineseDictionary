package com.example.chinesedictionary.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chinesedictionary.R
import com.example.chinesedictionary.databinding.ActivityFavoriteListBinding
import com.example.chinesedictionary.roomdb.FavoriteAdapter
import com.example.chinesedictionary.roomdb.FavoriteList

class FavoriteListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFavoriteListBinding
    private var rv: RecyclerView? = null
    private var adapter: FavoriteAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFavoriteListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_list)
        val button: Button = findViewById(R.id.btn_fav)
        rv = findViewById<View>(R.id.recyclerView) as RecyclerView
        rv!!.setHasFixedSize(true)
        rv!!.layoutManager = LinearLayoutManager(this)
        favData

        supportActionBar!!.title = "Favorites"

        button.setOnClickListener {
            finish()
        }
    }

    private val favData: Unit
        private get() {
            val favoriteLists = MainActivity.favoriteDatabase!!.favoriteDao()!!.getFavoriteData()
            adapter = FavoriteAdapter(favoriteLists, applicationContext,  object : FavoriteAdapter.OnAdapterListener {
                override fun onClick(result: FavoriteList) {
                startActivity(
                    Intent(this@FavoriteListActivity, FlashcardActivity::class.java)
                        .putExtra("intent_id", result.id)
                        .putExtra("intent_character", result.character)
                        .putExtra("intent_pinyin", result.pinyin)
                        .putExtra("intent_translation", result.translation)
                    )
                }
            })
            rv!!.adapter = adapter
        }
}