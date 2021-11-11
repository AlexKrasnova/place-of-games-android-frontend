package com.traineeship.placeofgames.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.textfield.TextInputLayout
import com.traineeship.placeofgames.R
import com.traineeship.placeofgames.utils.TokenUtil
import com.traineeship.placeofgames.viewmodels.ProfileViewModel

class ProfileFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModels()

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    private lateinit var btnMore: AppCompatImageButton

    private val tabTitles = listOf("Мои игры", "Запланировано")
    private val moreDialogItems = arrayOf("Выйти из аккаунта")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        initViews(view)

        viewPager.adapter = ProfileAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        profileViewModel.getUser().observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.tv_name).text = it.name
            view.findViewById<TextView>(R.id.tv_login).text = it.login
        }

        btnMore.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setItems(moreDialogItems) { dialog, which ->
                    exitFromAcc()
                }
                .show()
        }

        return view
    }

    private fun exitFromAcc(){
        TokenUtil(requireContext()).token = null
    }

    private fun initViews(view: View){
        viewPager = view.findViewById(R.id.vp_profile)
        tabLayout = view.findViewById(R.id.tab_profile)
        btnMore = view.findViewById(R.id.btn_more_profile)
    }
}