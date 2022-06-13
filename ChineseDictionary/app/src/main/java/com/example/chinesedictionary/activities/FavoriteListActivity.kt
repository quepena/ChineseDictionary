package com.example.chinesedictionary.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
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
        rv = findViewById<View>(R.id.recyclerView) as RecyclerView
        rv!!.setHasFixedSize(true)
        rv!!.layoutManager = LinearLayoutManager(this)
        favData

        var intentTitle = intent.getStringExtra("intent_title")
        supportActionBar!!.title = intentTitle
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

//        binding.btn3.setOnClickListener(View.OnClickListener {
//            startActivity(
//                Intent(
//                    this@FavoriteListActivity,
//                    MainActivity::class.java
//                )
//            )
//        })
        binding.btn3.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            startActivity(intent)
        })

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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