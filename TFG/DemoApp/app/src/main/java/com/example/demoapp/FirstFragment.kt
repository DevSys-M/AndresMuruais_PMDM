package com.example.demoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.adapter.AdaptadorCalendario

class FirstFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adaptadorCalendario: AdaptadorCalendario

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        recyclerView = view.findViewById(R.id.recyclerCalendar)

        setupRecyclerView()

        return view
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(requireContext(), 7)
        recyclerView.layoutManager = layoutManager

        val eventos = mutableMapOf<Int, List<String>>()
        // Agregar eventos para los días correspondientes

        adaptadorCalendario = AdaptadorCalendario(eventos)
        recyclerView.adapter = adaptadorCalendario
    }
}
