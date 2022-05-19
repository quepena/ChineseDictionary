package com.example.chinesedictionary.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chinesedictionary.R
import com.example.chinesedictionary.models.ExampleModel

class ExampleAdapter (var results: ArrayList<ExampleModel.Result>):
    RecyclerView.Adapter<ExampleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from( parent.context ).inflate( R.layout.adapter_examples,
            parent, false)
    )

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        holder.view.findViewById<TextView>(R.id.wordCharacter).text = result.characters
        holder.view.findViewById<TextView>(R.id.wordPinyin).text = result.pinyin
        holder.view.findViewById<TextView>(R.id.wordTranslation).text = result.translation
    }

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view)

    fun setData(data: List<ExampleModel.Result>){
        this.results.clear()
        this.results.addAll(data)
        notifyDataSetChanged()
    }
}