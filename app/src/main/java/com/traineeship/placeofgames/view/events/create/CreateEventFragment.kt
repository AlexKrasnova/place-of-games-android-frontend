package com.traineeship.placeofgames.view.events.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.traineeship.placeofgames.R
import com.traineeship.placeofgames.data.event.Category
import com.traineeship.placeofgames.data.event.NewEvent
import com.traineeship.placeofgames.utils.dateToQueryDate
import com.traineeship.placeofgames.viewmodels.CreateEventViewModel
import com.traineeship.placeofgames.viewmodels.PlacesViewModel

class CreateEventFragment : Fragment() {

    private val placesViewModel: PlacesViewModel by viewModels()
    private val createEventViewModel: CreateEventViewModel by activityViewModels()

    private lateinit var btnSetDateTime: MaterialButton
    private lateinit var tilEventName: TextInputLayout
    private lateinit var tilMaxNumberOfParticipants: TextInputLayout
    private lateinit var tilDesc: TextInputLayout
    private lateinit var tilCategory: TextInputLayout
    private lateinit var tilPlace: TextInputLayout
    private lateinit var tvDate: TextView
    private lateinit var tvTime: TextView
    private lateinit var btnCreate: MaterialButton
    private lateinit var btnBack: AppCompatImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_event, container, false)
        initViews(view)

        btnBack.setOnClickListener{
            findNavController().navigateUp()
        }

        btnSetDateTime.setOnClickListener {
            val id = createEventViewModel.placeId
            if (id != null) {
                findNavController().navigate(
                    CreateEventFragmentDirections.actionCreateEventFragmentToDateTimeFragment(
                        id
                    )
                )
            } else {
                Toast.makeText(requireContext(), "Выберите место!", Toast.LENGTH_SHORT).show()
            }
        }

        createEventViewModel.getSelectedDate().observe(viewLifecycleOwner) {
            tvDate.text = "Дата: $it"
        }

        createEventViewModel.getSelectedTime().observe(viewLifecycleOwner) {
            tvTime.text = "Время: $it"
        }

        setCategories()
        setPlaces()

        btnCreate.setOnClickListener {
            val name = tilEventName.editText!!.text.toString()
            val maxNumOfParticipants = tilMaxNumberOfParticipants.editText!!.text.toString()
            val desc = tilDesc.editText!!.text.toString()
            val category = tilCategory.editText!!.text.toString()
            val duration = createEventViewModel.getDuration()
            val placeId = createEventViewModel.placeId


            if (name.isEmpty()){
                Toast.makeText(requireContext(), "Название мероприятия не может быть пустым!", Toast.LENGTH_SHORT).show()
            } else if (maxNumOfParticipants.isEmpty() || maxNumOfParticipants.toInt() == 0) {
                Toast.makeText(requireContext(), "Выберите кол-во участников!", Toast.LENGTH_SHORT).show()
            }else if (category.isEmpty()){
                Toast.makeText(requireContext(), "Выберите категорию!", Toast.LENGTH_SHORT).show()
            } else if (duration == 0){
                Toast.makeText(requireContext(), "Выберите время!", Toast.LENGTH_SHORT).show()
            } else {
                val time = dateToQueryDate(tvDate.text.split(" ")[1]) +
                        "T" + tvTime.text.split(" ")[1] + ":00"
                val event = NewEvent(name, time, duration, placeId!!, maxNumOfParticipants.toInt(), desc, category)
                createEventViewModel.createEvent(event)

                createEventViewModel.getEventCreateCallback().observe(viewLifecycleOwner){
                    if (it){
                        Toast.makeText(requireContext(), "Мероприятие создано!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Не удалось создать мероприятие!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        return view
    }

    private fun setPlaces() {
        placesViewModel.getAutoCompletePlaces().observe(viewLifecycleOwner) {
            val places = it.map { place -> "${place.name}\n${place.address}" }.toList()
            val placesAdapter = ArrayAdapter(requireContext(), R.layout.item_autocomplete, places)
            (tilPlace.editText as AutoCompleteTextView).setAdapter(placesAdapter)

            (tilPlace.editText as AutoCompleteTextView).setOnItemClickListener { adapterView, view, i, l ->
                createEventViewModel.placeId = it[i].id
            }
        }
    }

    private fun setCategories() {
        val categoryItems = Category.values().toList().map { it.name }
        val categoryAdapter =
            ArrayAdapter(requireContext(), R.layout.item_autocomplete, categoryItems)
        (tilCategory.editText as AutoCompleteTextView).setAdapter(categoryAdapter)
    }

    private fun initViews(view: View) {
        btnSetDateTime = view.findViewById(R.id.btn_set_date_time)
        tilCategory = view.findViewById(R.id.til_category)
        tilPlace = view.findViewById(R.id.til_place)
        tvDate = view.findViewById(R.id.tv_date)
        tvTime = view.findViewById(R.id.tv_time)
        btnCreate = view.findViewById(R.id.btn_create_event)
        tilEventName = view.findViewById(R.id.til_event_name)
        tilDesc = view.findViewById(R.id.til_description)
        tilMaxNumberOfParticipants = view.findViewById(R.id.til_max_number_participants)
        btnBack = view.findViewById(R.id.btn_back)
    }
}