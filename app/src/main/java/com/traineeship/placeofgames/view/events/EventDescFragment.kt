package com.traineeship.placeofgames.view.events

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
import com.traineeship.placeofgames.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.traineeship.placeofgames.data.event.Event
import com.traineeship.placeofgames.viewmodels.EventDescViewModel

class EventDescFragment : Fragment() {

    private val eventViewModel: EventDescViewModel by viewModels()

    private val args: EventDescFragmentArgs by navArgs()
    private val eventId by lazy { args.eventId }

    private lateinit var tvEventName: TextView
    private lateinit var ivEvent: ImageView
    private lateinit var tvTime: TextView
    private lateinit var tvDuration: TextView
    private lateinit var tvWhere: TextView
    private lateinit var tvAddress: TextView
    private lateinit var tvPeopleNum: TextView
    private lateinit var tvDesc: TextView
    private lateinit var btnBack: AppCompatImageButton
    private lateinit var btnParticipants: AppCompatImageButton
    private lateinit var btnEventSignUp: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_event_desc, container, false)

        initViews(view)
        eventViewModel.getEvent(eventId).observe(viewLifecycleOwner, { event ->
            setEventToViews(event)
            btnParticipants.setOnClickListener {
                val items = event.participants?.map { it.name }?.toTypedArray()
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(resources.getString(R.string.participants))
                    .setItems(items) { dialog, which ->
                        // Respond to item chosen
                    }
                    .show()
            }
        })

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }



        return view
    }

    private fun setRightSignUpBtnText(event: Event){
        if (event.isCurrentUserEnrolled){
            btnEventSignUp.text = "Отменить"
        } else {
            btnEventSignUp.text = "Записаться"
        }
    }

    private fun setEventToViews(event: Event) {
        setRightSignUpBtnText(event)
        btnEventSignUp.setOnClickListener {
            if (!event.isCurrentUserEnrolled) {
                eventViewModel.incEventPeople(event.id)
            } else {
                eventViewModel.decEventPeople(event.id)
            }
        }

        val dateTime = event.time.split("T")
        val date = dateTime[0]
        val time = dateTime[1]

        tvEventName.text = event.name
        tvDesc.text = "Описание: ${event.description}"
        tvPeopleNum.text = "Кол-во участников: ${event.numberOfParticipants}/${event.maxNumberOfParticipants}"
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
        btnParticipants = view.findViewById(R.id.btn_participants)
        btnEventSignUp = view.findViewById(R.id.btn_event_sign_up)
    }
}
