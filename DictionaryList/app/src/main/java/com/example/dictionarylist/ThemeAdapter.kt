package com.example.dictionarylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ThemeAdapter(var items: ArrayList<Themes>, var clickListener: OnThemeItemClickListener) : RecyclerView.Adapter<ThemeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder {
        lateinit var themeViewHolder: ThemeViewHolder
        themeViewHolder = ThemeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_item_view, parent, false))

        return themeViewHolder
    }

    override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) {
        holder.initialize(items.get(position), clickListener)
    }

    override fun getItemCount(): Int {

        return items.size
    }

}

class ThemeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var themeName = itemView.findViewById<TextView>(R.id.themeName)
    var themeQuantity = itemView.findViewById<TextView>(R.id.themeQuantity)

    fun initialize(item: Themes, action: OnThemeItemClickListener) {
        themeName.text = item.name
        themeQuantity.text = item.quantity.toString()

        itemView.setOnClickListener{
            action.onItemClick(item, absoluteAdapterPosition)
        }
    }
}

interface OnThemeItemClickListener {
    fun onItemClick(item: Themes, position: Int)
}