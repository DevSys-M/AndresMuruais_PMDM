package com.example.demoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R

class AdaptadorCalendario : RecyclerView.Adapter<AdaptadorCalendario.ViewHolder>() {

    private val eventosPorDia = mutableMapOf<Int, List<String>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_calendario, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val day = position + 1
        val eventsForDay = getEventsForDay(day)

        holder.tvDay.text = day.toString()

        if (eventsForDay.isNotEmpty()) {
            holder.tvDay.setBackgroundResource(R.drawable.background_yellow_circle)
        } else {
            holder.tvDay.background = null
        }

        holder.itemView.setOnClickListener {
            // Lógica para manejar el clic en un día del calendario
        }

        holder.itemView.setOnLongClickListener {
            // Lógica para manejar el clic largo en un día del calendario
            true
        }
    }

    override fun getItemCount(): Int {
        return 31 // Número total de días en el calendario
    }

    fun getEventsForDay(day: Int): List<String> {
        return eventosPorDia[day] ?: emptyList()
    }

    fun addEvent(day: Int, event: String) {
        val events = eventosPorDia[day]?.toMutableList() ?: mutableListOf()
        events.add(event)
        eventosPorDia[day] = events
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDay: TextView = itemView.findViewById(R.id.tvDay)
    }
}


