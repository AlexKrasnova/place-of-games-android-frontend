package com.example.placeofgames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EventsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val eventsViewModel: EventListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_events, container, false)

        recyclerView = view.findViewById(R.id.rv_places)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = EventsAdapter(listOf())
        getEvents()

        return view
    }

    private fun getEvents() {
        eventsViewModel.getEvents().observe(viewLifecycleOwner, { events ->
            recyclerView.adapter = EventsAdapter(events)
        })
    }
}