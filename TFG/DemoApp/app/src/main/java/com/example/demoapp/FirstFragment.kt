package com.example.demoapp

import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demoapp.adapter.AdaptadorCalendario
import com.example.demoapp.databinding.FragmentFirstBinding
import com.example.demoapp.dialog.AddEventDialog
import com.example.demoapp.dialog.EventDetailDialog
import com.github.sundeepk.compactcalendarview.CompactCalendarView

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(),  AdaptadorCalendario.OnDateClickListener, AddEventDialog.OnEventAddedListener, EventDetailDialog.OnEventDetailDismissedListener {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adaptadorCalendario: AdaptadorCalendario


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adaptadorCalendario = AdaptadorCalendario(requireContext(), this)
        binding.recyclerCalendar.apply {
            layoutManager = GridLayoutManager(requireContext(), 7)
            adapter = adaptadorCalendario
        }

        binding.buttonShowEvents.setOnClickListener {
            val events = adaptadorCalendario.getAllEvents().values.toList()
            if (events.isNotEmpty()) {
                val eventDetailDialog = EventDetailDialog.newInstance(events)
                eventDetailDialog.setOnEventDetailDismissedListener(this)
                eventDetailDialog.show(childFragmentManager, "EventDetailDialog")
            } else {
                Toast.makeText(requireContext(), "No hay eventos", Toast.LENGTH_SHORT).show()
            }
        }

    }
    override fun onDateClick(date: String) {
        val addEventDialog = AddEventDialog.newInstance()
        addEventDialog.setOnEventAddedListener(this)
        addEventDialog.show(childFragmentManager, "AddEventDialog")
    }

    override fun onEventAdded(eventText: String) {
        adaptadorCalendario.addEvent(eventText)
    }

    override fun onEventDetailDismissed() {
        // Aquí puedes realizar alguna acción después de cerrar el diálogo de detalles del evento
    }


}