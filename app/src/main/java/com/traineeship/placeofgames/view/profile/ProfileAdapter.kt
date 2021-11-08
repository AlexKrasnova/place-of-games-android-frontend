package com.traineeship.placeofgames.view.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.traineeship.placeofgames.view.events.EventsFragment

class ProfileAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            Events.OWNED.ordinal -> {
                val fragment = EventsFragment()
                fragment.arguments = Bundle().apply {
                    putString(
                        EventsFragment.Constants.EVENTS_TYPE,
                        EventsFragment.Constants.TYPE_OWNED_EVENTS
                    )
                }

                return fragment
            }
            Events.PARTICIPATE.ordinal -> {
                val fragment = EventsFragment()
                fragment.arguments = Bundle().apply {
                    putString(
                        EventsFragment.Constants.EVENTS_TYPE,
                        EventsFragment.Constants.TYPE_PARTICIPATE_EVENTS
                    )
                }

                return fragment
            }
            else -> return EventsFragment()
        }
    }

    enum class Events{
        OWNED,
        PARTICIPATE
    }
}