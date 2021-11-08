package com.traineeship.placeofgames.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.textfield.TextInputLayout
import com.traineeship.placeofgames.R
import com.traineeship.placeofgames.viewmodels.ProfileViewModel

class ProfileFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModels()

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        viewPager = view.findViewById(R.id.vp_profile)
        viewPager.adapter = ProfileAdapter(this)

        val tabTitles = listOf("Мои игры", "Запланировано")
        tabLayout = view.findViewById(R.id.tab_profile)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        profileViewModel.getUser().observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.tv_name).text = it.name
            view.findViewById<TextView>(R.id.tv_login).text = it.login
        }

        return view
    }
}