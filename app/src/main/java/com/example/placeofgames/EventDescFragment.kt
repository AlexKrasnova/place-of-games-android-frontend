package com.example.placeofgames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.placeofgames.data.Event
import com.example.placeofgames.viewmodels.EventViewModel
import com.google.android.material.button.MaterialButton
import java.util.*

class EventDescFragment : Fragment() {

    private lateinit var event: Event
    private val eventViewModel: EventViewModel by viewModels()

    private lateinit var tvEventName: TextView
    private lateinit var ivEvent: ImageView
    private lateinit var tvTime: TextView
    private lateinit var tvDuration: TextView
    private lateinit var tvWhere: TextView
    private lateinit var tvAddress: TextView
    private lateinit var tvPeopleNum: TextView
    private lateinit var tvDesc: TextView
    private lateinit var btnBack: AppCompatImageButton
    private lateinit var btnEventSignUp: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_event_desc, container, false)
        event = requireArguments().getParcelable("event")!!
        initViews(view)

        setEventToViews(event)

        event.id.let {
            eventViewModel.getEventLiveData(it).observe(viewLifecycleOwner, { event ->

                if (this.event != event) {
                    this.event = event
                    setEventToViews(event)
                }

            })
        }

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        btnEventSignUp.setOnClickListener {
            eventViewModel.incEventPeople(event.id)
        }

        return view
    }

    private fun setEventToViews(event: Event) {
        val dateTime = event.time?.split("T")
        val date = dateTime?.get(0)
        val time = dateTime?.get(1)

        tvEventName.text = event.name
        tvDesc.text = "Описание: ${event.description}"
        tvPeopleNum.text = "Кол-во участников: ${event.currentPeopleNum}/${event.maxPeopleNum}"
        tvAddress.text = "Адрес: ${event.place?.address}"
        tvWhere.text = "Место: ${event.place?.name}"
        tvDuration.text = "Продолжительность: ${event.duration} мин"
        tvTime.text = "Время: ${time}, ${date}"
    }

    private fun initViews(view: View) {
        tvEventName = view.findViewById(R.id.tv_event_name)
        ivEvent = view.findViewById(R.id.iv_event)
        tvTime = view.findViewById(R.id.tv_time)
        tvDuration = view.findViewById(R.id.tv_duration)
        tvWhere = view.findViewById(R.id.tv_place)
        tvAddress = view.findViewById(R.id.tv_address)
        tvPeopleNum = view.findViewById(R.id.tv_people_num)
        tvDesc = view.findViewById(R.id.tv_desc)
        btnBack = view.findViewById(R.id.btn_back)
        btnEventSignUp = view.findViewById(R.id.btn_event_sign_up)
    }
}
