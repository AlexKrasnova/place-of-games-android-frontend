package com.example.placeofgames

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.placeofgames.data.Event

class EventsAdapter(private val data: List<Event>) : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val iv: ImageView = view.findViewById(R.id.iv_event)
        val tvName: TextView = view.findViewById(R.id.tv_event_name)
        val tvPeople: TextView = view.findViewById(R.id.tv_people_num)
        val tvAddress: TextView = view.findViewById(R.id.tv_event_address)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = data[position]

        //todo refactor
        holder.iv.setImageDrawable(ResourcesCompat.getDrawable(holder.itemView.resources, R.drawable.dog, holder.itemView.context.theme))

        holder.tvName.text = event.name
        holder.tvAddress.text = event.place.address
        holder.tvPeople.text = "Кол-во участников: ${event.currentPeopleNum}/${event.maxPeopleNum}"
    }

    override fun getItemCount(): Int {
        return data.size
    }
}