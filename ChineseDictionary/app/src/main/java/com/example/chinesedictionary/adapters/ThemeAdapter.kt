package com.example.chinesedictionary.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chinesedictionary.R
import com.example.chinesedictionary.models.MainModel
import kotlin.collections.ArrayList

class ThemeAdapter (var results: ArrayList<MainModel.Result>, val listener: OnAdapterListener):
    RecyclerView.Adapter<ThemeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
            LayoutInflater.from( parent.context ).inflate( R.layout.adapter_themes,
                    parent, false)
    )

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        holder.view.findViewById<TextView>(R.id.themeName).text = result.name
        holder.view.findViewById<TextView>(R.id.themeQuantity).text = result.quantity.toString()
        holder.view.setOnClickListener { listener.onClick( result ) }
    }

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view)

    fun setData(data: List<MainModel.Result>){
        this.results.clear()
        this.results.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(result: MainModel.Result)
    }
}