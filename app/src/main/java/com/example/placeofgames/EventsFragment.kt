package com.example.placeofgames

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class EventsFragment : Fragment(), EventsAdapter.OnSignUpClickListener{

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private val eventsViewModel: EventListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_events, container, false)

        initViews(view)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = EventsAdapter(mutableListOf(), this)
        getEvents()

        swipeRefresh.setOnRefreshListener {
            eventsViewModel.loadEvents()
        }

        return view
    }

    private fun getEvents() {
        eventsViewModel.getEvents().observe(viewLifecycleOwner, { events ->
            recyclerView.adapter = EventsAdapter(events, this)
            swipeRefresh.isRefreshing = false
        })
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.rv_places)
        swipeRefresh = view.findViewById(R.id.swipe_refresh_events)
    }

    override fun onClickSignUp(v: View, eventId: Int) {
        eventsViewModel.incEventPeople(eventId)
    }
}