package com.example.placeofgames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.placeofgames.data.Event
import com.example.placeofgames.data.Place
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val eventsViewModel: EventListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv_places)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = EventsAdapter(listOf())
        getEvents()
    }

    private fun getEvents(){
        eventsViewModel.getEvents().observe(this, { events ->
                recyclerView.adapter = EventsAdapter(events)
        })
    }
}
