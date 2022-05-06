package com.example.dictionarylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WordAdapter(var items: ArrayList<Words>) : RecyclerView.Adapter<WordViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        lateinit var wordViewHolder: WordViewHolder
        wordViewHolder = WordViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_words, parent, false))

        return wordViewHolder
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.initialize(items.get(position))
    }

    override fun getItemCount(): Int {

        return items.size
    }

}

class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var wordCharacter = itemView.findViewById<TextView>(R.id.wordCharacter)
    var wordPinyin = itemView.findViewById<TextView>(R.id.wordPinyin)
    var wordTranslation = itemView.findViewById<TextView>(R.id.wordTranslation)

    fun initialize(item: Words) {
        wordCharacter.text = item.character
        wordPinyin.text = item.pinyin
        wordTranslation.text = item.translation
    }
}