package com.example.demoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demoapp.adapter.AdaptadorCalendario
import com.example.demoapp.databinding.FragmentFirstBinding
import com.example.demoapp.dialog.AddEventDialog
import com.example.demoapp.dialog.EventDetailDialog

class FirstFragment : Fragment(), AdaptadorCalendario.OnDateClickListener, AddEventDialog.OnEventAddedListener, EventDetailDialog.OnEventDetailDismissedListener {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private lateinit var adaptadorCalendario: AdaptadorCalendario

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            val calendarAdapter = AdaptadorCalendario()
            recyclerView.adapter = calendarAdapter

            // Configura el layout manager y el divisor de la cuadrícula
            val gridLayoutManager = GridLayoutManager(requireContext(), 7)
            recyclerView.layoutManager = gridLayoutManager
            recyclerView.addItemDecoration(GridSpacingItemDecoration(7, 16, false))

            // Agrega el escuchador de clics en el calendario
            calendarAdapter.setOnItemClickListener(object : AdaptadorCalendario.OnItemClickListener {
                override fun onItemClick(day: Int) {
                    val eventsForDay = calendarAdapter.getEventsForDay(day)
                    val eventText = calendarAdapter.buildEventText(eventsForDay)
                    showEventDetailDialog(requireContext(), eventText)
                }

                override fun onItemLongClick(day: Int) {
                    showAddEventDialog(requireContext(), day)
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDateClick(date: String) {
        val addEventDialog = AddEventDialog.newInstance(date)
        addEventDialog.setOnEventAddedListener(this)
        addEventDialog.show(childFragmentManager, "AddEventDialog")
    }

    override fun onEventAdded(date: String, eventText: String) {
        adaptadorCalendario.addEvent(date, eventText)
    }

    override fun onEventDetailDismissed() {
        // Aquí puedes realizar alguna acción después de cerrar el diálogo de detalles del evento
    }
}
