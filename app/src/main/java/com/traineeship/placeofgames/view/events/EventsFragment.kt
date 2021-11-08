package com.traineeship.placeofgames.view.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.traineeship.placeofgames.R
import com.traineeship.placeofgames.data.event.Event
import com.traineeship.placeofgames.view.profile.ProfileFragmentDirections
import com.traineeship.placeofgames.viewmodels.EventsViewModel


class EventsFragment : Fragment(), EventsAdapter.EventClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private val eventsViewModel: EventsViewModel by viewModels()
    private var type: String = Constants.TYPE_STANDARD

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_events, container, false)

        initViews(view)

        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = EventsAdapter(mutableListOf(), this)

        arguments?.takeIf { it.containsKey(Constants.EVENTS_TYPE) }?.apply {
            type = getString(Constants.EVENTS_TYPE)!!
        }

        observeEvents()

        swipeRefresh.setOnRefreshListener {
            loadTypedEvents()
        }

        eventsViewModel.getUpdatedEvent().observe(viewLifecycleOwner, { event ->
            (recyclerView.adapter as EventsAdapter).updateEvent(event)
        })

        return view
    }

    private fun loadTypedEvents() {
        when (type) {
            Constants.TYPE_STANDARD -> eventsViewModel.loadEvents()
            Constants.TYPE_OWNED_EVENTS -> eventsViewModel.loadOwnedEvents()
            Constants.TYPE_PARTICIPATE_EVENTS -> eventsViewModel.loadParticipateEvents()
        }
    }

    private fun observeEvents() {
        when (type) {
            Constants.TYPE_STANDARD -> {
                eventsViewModel.getEvents().observe(viewLifecycleOwner, {
                    recyclerView.adapter = EventsAdapter(it, this)
                    swipeRefresh.isRefreshing = false
                })
            }

            Constants.TYPE_OWNED_EVENTS -> {
                eventsViewModel.getOwnedEvents().observe(viewLifecycleOwner) {
                    recyclerView.adapter = EventsAdapter(it, this)
                    swipeRefresh.isRefreshing = false
                }
            }

            Constants.TYPE_PARTICIPATE_EVENTS -> {
                eventsViewModel.getParticipateEvents().observe(viewLifecycleOwner) {
                    recyclerView.adapter = EventsAdapter(it, this)
                    swipeRefresh.isRefreshing = false
                }
            }
        }

    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.rv_places)
        swipeRefresh = view.findViewById(R.id.swipe_refresh_places)
    }

    override fun onClickSignUp(v: View, event: Event) {
        if (!event.isCurrentUserEnrolled) {
            eventsViewModel.incEventPeople(event.id)
        } else {
            eventsViewModel.decEventPeople(event.id)
        }
    }

    override fun onClickItem(v: View, event: Event) {
        findNavController().navigate(
            if (type == Constants.TYPE_STANDARD) {
                EventsFragmentDirections.actionToEventDesc(event.id)
            } else {
                ProfileFragmentDirections.actionProfileFragmentToEventDescFragment(event.id)
            }
        )
    }

    object Constants {
        const val EVENTS_TYPE = "EVENTS_TYPE"
        const val TYPE_OWNED_EVENTS = "OWNED_EVENTS"
        const val TYPE_PARTICIPATE_EVENTS = "PARTICIPATE_EVENTS"
        const val TYPE_STANDARD = "STANDARD"
    }
}