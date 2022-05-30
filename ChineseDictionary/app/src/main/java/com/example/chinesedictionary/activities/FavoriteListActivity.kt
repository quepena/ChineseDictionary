package com.example.chinesedictionary.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chinesedictionary.roomdb.FavoriteAdapter
import com.example.chinesedictionary.roomdb.FavoriteList
import com.example.chinesedictionary.R

//class FavoriteListActivity : AppCompatActivity() {
//
//    private lateinit var binding : ActivityFavoriteListBinding
//
//    private lateinit var favoriteListAdapter: FavoriteAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityFavoriteListBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        getFavData()
//
//        binding.recyclerView.layoutManager = LinearLayoutManager(this@FavoriteListActivity)
//        binding.recyclerView.adapter = favoriteListAdapter
//    }
//
//    private fun getFavData() {
//        val favoriteLists: List<FavoriteList>? = MainActivity().favoriteDatabase.favoriteDao()?.getFavoriteData()
//        Log.i("favs", favoriteLists.toString())
//        favoriteListAdapter = FavoriteAdapter(favoriteLists, applicationContext)
////        binding.recyclerView.adapter = favoriteListAdapter
//    }
//}

class FavoriteListActivity : AppCompatActivity() {
    private var rv: RecyclerView? = null
    private var adapter: FavoriteAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_list)
        rv = findViewById<View>(R.id.recyclerView) as RecyclerView
        rv!!.setHasFixedSize(true)
        rv!!.layoutManager = LinearLayoutManager(this)
        favData
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
//                    Log.i("wtf", result.toString())
                }
            })
            rv!!.adapter = adapter

//            adapter = FavoriteAdapter(arrayListOf(), applicationContext, object : FavoriteAdapter.OnAdapterListener {
//                override fun onClick(result: FavoriteList) {
////                startActivity(
////                    Intent(this@FavoriteListActivity, DetailActivity::class.java)
////                        .putExtra("intent_id", result.id)
////                )
//                    Log.i("wtf", result.toString())
//                }
//            })
        }
}