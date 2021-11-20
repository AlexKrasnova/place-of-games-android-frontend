package com.traineeship.placeofgames.view.events.create

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.traineeship.placeofgames.R
import com.traineeship.placeofgames.repository.places.PlacesService.Companion.TAG
import com.traineeship.placeofgames.utils.freeTimeToTime
import com.traineeship.placeofgames.viewmodels.CreateEventViewModel
import com.traineeship.placeofgames.viewmodels.SetDateTimeVewModel
import java.text.SimpleDateFormat
import java.util.*

class SetDateTimeFragment : Fragment() {

    private val args: SetDateTimeFragmentArgs by navArgs()
    private val placeId by lazy { args.placeId }

    private val setDateTimeViewModel: SetDateTimeVewModel by viewModels()
    private val createEventViewModel: CreateEventViewModel by activityViewModels()

    private lateinit var rvTime: RecyclerView
    private lateinit var btnDate: MaterialButton
    private lateinit var btnBack: AppCompatImageButton
    private lateinit var fabSave: ExtendedFloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_set_date_time, container, false)
        initViews(view)

        setDate(Date())

        setDateTimeViewModel.getFreeTime().observe(viewLifecycleOwner) {
            val time = freeTimeToTime(it)
            Log.d(TAG, "onCreateView: $time")
            rvTime.adapter = TimeAdapter(time)
        }

        btnDate.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Выберете дату")
                    .build()
            datePicker.show(childFragmentManager, datePicker.toString())

            datePicker.addOnPositiveButtonClickListener {
                val date = Date(it)
                setDate(date)
            }
        }

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        fabSave.setOnClickListener {
            saveSelectedTime()

        }

        return view
    }

    private fun saveSelectedTime() {
        val data = (rvTime.adapter as TimeAdapter).data
        val (firstIndex, firstSelectedTime) = Pair(
            data.indexOfFirst { it.isSelected },
            data.firstOrNull { it.isSelected })
        val (lastSelectedIndex, lastSelectedTime) = Pair(data.indexOfLast { it.isSelected }, data.lastOrNull { it.isSelected })

        if (firstSelectedTime == null) {
            Toast.makeText(requireContext(), "Выберите время!", Toast.LENGTH_SHORT).show()
        } else {

            val selected = data.subList(firstIndex, lastSelectedIndex)

            if (selected.any { !it.isSelected }) {
                Toast.makeText(
                    requireContext(),
                    "Выберите промежутки времени, идущие подряд",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val startTime = firstSelectedTime.start
                val endTime = lastSelectedTime!!.end
                val duration = (selected.size + 1) * 30

                createEventViewModel.setDuration(duration)
                createEventViewModel.setSelectedTime("$startTime - $endTime")

                Toast.makeText(requireContext(), "Сохранено!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setDate(date: Date) {
        val formattedDate = SimpleDateFormat("dd-MM-yyyy").format(date).toString()
        btnDate.text = formattedDate
        val dateToQuery = SimpleDateFormat("yyyy-MM-dd").format(date).toString()
        setDateTimeViewModel.loadFreeTime(placeId, dateToQuery)

        createEventViewModel.setSelectedDate(formattedDate)
    }

    private fun initViews(view: View) {
        rvTime = view.findViewById(R.id.rv_time)
        btnDate = view.findViewById(R.id.btn_date)
        btnBack = view.findViewById(R.id.btn_back)
        fabSave = view.findViewById(R.id.fab_save_date_time)
    }
}