package com.example.chinesedictionary.adapters

import android.R.attr.tag
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.chinesedictionary.R
import com.example.chinesedictionary.activities.MainActivity
import com.example.chinesedictionary.activities.WordsActivity
import com.example.chinesedictionary.fragments.FragmentFlashcardReveal
import com.example.chinesedictionary.models.WordsModel
import com.example.chinesedictionary.roomdb.FavoriteList


class WordAdapter (var results: ArrayList<WordsModel.Result>, val listener: OnAdapterListener):
    RecyclerView.Adapter<WordAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from( parent.context ).inflate( R.layout.adapter_word,
            parent, false)
    )

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        Log.i("resik", result.toString())
        if(result.text != null) {
//            holder.view.findViewById<TextView>(R.id.wordCharacter).text = result.text
//            holder.view.findViewById<TextView>(R.id.wordPinyin).text = ""
//            holder.view.findViewById<TextView>(R.id.wordTranslation).text = ""
            holder.view.findViewById<TextView>(R.id.error_text).text = result.text
            holder.view.findViewById<Button>(R.id.favbtn).background = null
            holder.view.findViewById<TextView>(R.id.wordPinyin).text = ""
            holder.view.findViewById<TextView>(R.id.wordTranslation).text = ""
        } else {
            holder.view.findViewById<TextView>(R.id.error_text).text = ""
            holder.view.findViewById<TextView>(R.id.wordCharacter).text = result.character
            holder.view.findViewById<TextView>(R.id.wordPinyin).text = result.pinyin
            holder.view.findViewById<TextView>(R.id.wordTranslation).text = result.translation
            holder.view.setOnClickListener { listener.onClick(result) }

            if (MainActivity.favoriteDatabase?.favoriteDao()!!.isFavorite(result._id) == 1)
                holder.view.findViewById<Button>(R.id.favbtn)
                    .setBackgroundResource(R.drawable.ic_baseline_star_rate_24)
            else
                holder.view.findViewById<Button>(R.id.favbtn)
                    .setBackgroundResource(R.drawable.ic_baseline_star_outline_24)

            holder.view.findViewById<Button>(R.id.favbtn)
                .setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        val favoriteList = FavoriteList()
                        val id = result._id
                        val character = result.character
                        val pinyin = result.pinyin
                        val translation = result.translation

                        favoriteList.id = id
                        favoriteList.character = character
                        favoriteList.pinyin = pinyin
                        favoriteList.translation = translation

                        if (MainActivity.favoriteDatabase?.favoriteDao()!!.isFavorite(id) !== 1) {
                            holder.view.findViewById<Button>(R.id.favbtn)
                                .setBackgroundResource(R.drawable.ic_baseline_star_rate_24)
                            MainActivity.favoriteDatabase?.favoriteDao()!!.addData(favoriteList)
                        } else {
                            holder.view.findViewById<Button>(R.id.favbtn)
                                .setBackgroundResource(R.drawable.ic_baseline_star_outline_24)
                            MainActivity.favoriteDatabase?.favoriteDao()?.delete(favoriteList)
                        }
                    }
                })
        }
    }

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view)

    fun setData(data: List<WordsModel.Result>){
        this.results.clear()
        this.results.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(result: WordsModel.Result)
    }
}