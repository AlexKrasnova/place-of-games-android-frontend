package com.traineeship.placeofgames.view.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.placeofgames.R
import com.google.android.material.button.MaterialButton
import com.traineeship.placeofgames.data.event.Event

class EventsAdapter(
    private val data: MutableList<Event>,
    private val eventClickListener: EventClickListener
) : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iv: ImageView = view.findViewById(R.id.iv_event)
        val tvName: TextView = view.findViewById(R.id.tv_event_name)
        val tvPeople: TextView = view.findViewById(R.id.tv_people_num)
        val tvAddress: TextView = view.findViewById(R.id.tv_place_address)
        var btnSignUp: MaterialButton = view.findViewById(R.id.btn_event_sign_up)
        val layoutEvent: ConstraintLayout = view.findViewById(R.id.layout_event)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = data[position]

        //todo refactor
        holder.iv.setImageDrawable(
            ResourcesCompat.getDrawable(
                holder.itemView.resources,
                R.drawable.dog, holder.itemView.context.theme
            )
        )

        if (event.isCurrentUserEnrolled) {
            holder.btnSignUp.text = "Отменить"
        } else {
            holder.btnSignUp.text = "Записаться"
        }

        holder.tvName.text = event.name
        holder.tvAddress.text = event.place.address
        holder.tvPeople.text = "Кол-во участников: ${event.numberOfParticipants}/${event.maxNumberOfParticipants}"
        holder.btnSignUp.setOnClickListener {
            eventClickListener.onClickSignUp(it, event)
        }
        holder.layoutEvent.setOnClickListener {
            eventClickListener.onClickItem(it, event)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateEvent(updatedEvent: Event) {
        val updateIndex = data.indexOfFirst { event -> event.id == updatedEvent.id }
        data[updateIndex] = updatedEvent
        notifyItemChanged(updateIndex)
    }

    interface EventClickListener {
        fun onClickSignUp(v: View, event: Event)
        fun onClickItem(v: View, event: Event)
    }
}

