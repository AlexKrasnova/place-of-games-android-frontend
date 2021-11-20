package com.traineeship.placeofgames.view.places

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.traineeship.placeofgames.R
import com.traineeship.placeofgames.data.place.Place
import com.traineeship.placeofgames.viewmodels.PlaceDescViewModel

class PlaceDescFragment : Fragment() {

    private val placeViewModel: PlaceDescViewModel by viewModels()

    private val args: PlaceDescFragmentArgs by navArgs()
    private val placeId by lazy { args.placeId }

    private lateinit var btnBack: AppCompatImageButton
    private lateinit var tvName: TextView
    private lateinit var tvAddress: TextView
    private lateinit var tvDesc: TextView
    private lateinit var tvWorkingHours: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_place_desc, container, false)
        initViews(view)

        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        placeViewModel.getPlace(placeId).observe(viewLifecycleOwner) {
            setPlaceToViews(it)
        }

        return view
    }

    private fun setPlaceToViews(place: Place) {
        val formattedWorkingHours = StringBuilder()

        place.workingHoursList.forEach { formattedWorkingHours.append("$it\n") }

        tvName.text = place.name
        tvAddress.text = "Адрес: ${place.address}"
        tvDesc.text = "Описание: ${place.description}"
        tvWorkingHours.text = formattedWorkingHours.toString()
    }

    private fun initViews(view: View){
        btnBack = view.findViewById(R.id.btn_back)
        tvName = view.findViewById(R.id.tv_place_name)
        tvAddress = view.findViewById(R.id.tv_address)
        tvDesc = view.findViewById(R.id.tv_desc)
        tvWorkingHours = view.findViewById(R.id.tv_working_hours)
    }
}