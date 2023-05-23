package com.example.demoapp

import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demoapp.adapter.AdaptadorCalendario
import com.example.demoapp.databinding.FragmentFirstBinding
import com.github.sundeepk.compactcalendarview.CompactCalendarView

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adaptadorCalendario: AdaptadorCalendario
    private lateinit var compactCalendarView: CompactCalendarView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val days = getDaysOfMonth()
        val events = listOf("5", "12", "20") // Ejemplo de fechas con eventos
        adaptadorCalendario = AdaptadorCalendario(requireContext(),days,events)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onStart() {
        super.onStart()

        binding.recyclerCalendar.adapter = adaptadorCalendario
        binding.recyclerCalendar.layoutManager = GridLayoutManager(requireContext(),7)

    }



    private fun getDaysOfMonth(): List<String> {
        val calendar = Calendar.getInstance()
        val days = ArrayList<String>()

        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        for (i in 1..maxDay) {
            val day = i.toString()
            days.add(day)
        }

        return days
    }

}