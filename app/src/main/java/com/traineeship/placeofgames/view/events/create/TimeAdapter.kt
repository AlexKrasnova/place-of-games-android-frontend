package com.traineeship.placeofgames.view.events.create

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.traineeship.placeofgames.R
import com.traineeship.placeofgames.data.place.Time

class TimeAdapter(val data: List<Time>) : RecyclerView.Adapter<TimeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTime: TextView = view.findViewById(R.id.tv_time)
        val layout: MaterialCardView = view.findViewById(R.id.layout_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_time, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val time = data[position]
        holder.tvTime.text = time.toString()

        if (!time.isFree) {
            holder.layout.setCardBackgroundColor(holder.itemView.resources.getColor(R.color.gray))
        } else {

            setTimeBackground(time, holder)

            holder.layout.setOnClickListener {
                time.isSelected = !time.isSelected
                setTimeBackground(time, holder)
            }
        }
    }

    private fun setTimeBackground(
        time: Time,
        holder: ViewHolder
    ) {
        if (time.isSelected) {
            holder.layout.setCardBackgroundColor(holder.itemView.resources.getColor(R.color.purple_500))
            holder.tvTime.setTextColor(holder.itemView.resources.getColor(R.color.white))
        } else {
            holder.layout.setCardBackgroundColor(holder.itemView.resources.getColor(R.color.white))
            holder.tvTime.setTextColor(holder.itemView.resources.getColor(android.R.color.tab_indicator_text))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}