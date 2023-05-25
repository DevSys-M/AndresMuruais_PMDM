package com.example.demoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        adaptadorCalendario = AdaptadorCalendario(requireContext(), this)
        binding.recyclerCalendar.apply {
            layoutManager = GridLayoutManager(requireContext(), 7)
            adapter = adaptadorCalendario
        }

        binding.buttonShowEvents.setOnClickListener {
            val events = adaptadorCalendario.getAllEvents().values.flatten()
            if (events.isNotEmpty()) {
                val eventDetailDialog = EventDetailDialog.newInstance(events)
                eventDetailDialog.setOnEventDetailDismissedListener(this)
                eventDetailDialog.show(childFragmentManager, "EventDetailDialog")
            } else {
                Toast.makeText(requireContext(), "No hay eventos", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDateClick(date: String) {
        Toast.makeText(requireContext(), "Fecha seleccionada: $date", Toast.LENGTH_SHORT).show()
    }

    override fun onEventAdded(date: String, eventText: String) {
        adaptadorCalendario.addEvent(date, eventText)
        adaptadorCalendario.notifyDataSetChanged()
    }

    override fun onEventDetailDismissed() {
        // Aquí puedes realizar alguna acción después de cerrar el diálogo de detalles del evento
    }
}
