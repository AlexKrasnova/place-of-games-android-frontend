package com.traineeship.placeofgames.view.places

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.traineeship.placeofgames.R
import com.traineeship.placeofgames.data.event.Place

class PlacesAdapter(
    private val data: MutableList<Place>,
    private val placeClickListener: PlaceClickListener
) :
    RecyclerView.Adapter<PlacesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val layout: ConstraintLayout = view.findViewById(R.id.layout_place)
        val iv: ImageView = view.findViewById(R.id.iv_place)
        val tvName: TextView = view.findViewById(R.id.tv_place_name)
        val tvAddress: TextView = view.findViewById(R.id.tv_place_address)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_place, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = data[position]

        //todo
        holder.iv.setImageDrawable(
            ResourcesCompat.getDrawable(
                holder.itemView.resources,
                R.drawable.dog, holder.itemView.context.theme
            )
        )

        holder.tvName.text = place.name
        holder.tvAddress.text = place.address
        holder.layout.setOnClickListener {
            placeClickListener.onClickItem(it, place.id)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface PlaceClickListener {
        fun onClickItem(view: View, placeId: Int)
    }
}