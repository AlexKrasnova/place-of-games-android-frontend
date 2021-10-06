package com.example.placeofgames

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.TypefaceCompat
import androidx.recyclerview.widget.RecyclerView

class PlacesAdapter(private val data: ArrayList<String>) : RecyclerView.Adapter<PlacesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val iv: ImageView = view.findViewById(R.id.iv_place)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_place, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.iv.setImageDrawable(ResourcesCompat.getDrawable(holder.itemView.resources, R.drawable.dog, holder.itemView.context.theme))
    }

    override fun getItemCount(): Int {
        return data.size
    }
}