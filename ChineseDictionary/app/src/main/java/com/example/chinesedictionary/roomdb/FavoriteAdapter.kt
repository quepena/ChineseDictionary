package com.example.chinesedictionary.roomdb

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chinesedictionary.R

class FavoriteAdapter(private val favoriteLists: List<FavoriteList>?, var context: Context, val listener: OnAdapterListener) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.adapter_favorite_word, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val fl = favoriteLists!!.get(i)
        viewHolder.tv.text = fl.character
        viewHolder.pinyin.text = fl.pinyin
        viewHolder.translation.text = fl.translation

        viewHolder.itemView.setOnClickListener { listener.onClick( fl ) }
    }

    override fun getItemCount(): Int {
        return favoriteLists!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv: TextView
        var pinyin: TextView
        var translation: TextView

        init {
            tv = itemView.findViewById<View>(R.id.wordCharacter) as TextView
            pinyin = itemView.findViewById<View>(R.id.wordPinyin) as TextView
            translation = itemView.findViewById<View>(R.id.wordTranslation) as TextView
        }
    }

    interface OnAdapterListener {
        fun onClick(result: FavoriteList)
    }
}