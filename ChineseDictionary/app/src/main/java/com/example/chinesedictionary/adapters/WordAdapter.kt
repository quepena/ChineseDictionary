package com.example.chinesedictionary.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chinesedictionary.R
import com.example.chinesedictionary.models.WordsModel

class WordAdapter (var results: ArrayList<WordsModel.Result>, val listener: OnAdapterListener):
    RecyclerView.Adapter<WordAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from( parent.context ).inflate( R.layout.adapter_word,
            parent, false)
    )

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        holder.view.findViewById<TextView>(R.id.wordCharacter).text = result.character
        holder.view.findViewById<TextView>(R.id.wordPinyin).text = result.pinyin
        holder.view.findViewById<TextView>(R.id.wordTranslation).text = result.translation
        holder.view.setOnClickListener { listener.onClick( result ) }
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