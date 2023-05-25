package com.example.demoapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.dialog.AddEventDialog
import com.example.demoapp.dialog.EventDetailDialog

class AdaptadorCalendario(private val eventos: Map<Int, List<String>>) :
    RecyclerView.Adapter<AdaptadorCalendario.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calendario, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dia = position + 1
        val eventosDia = eventos[dia]

        holder.tvDia.text = dia.toString()

        if (eventosDia.isNullOrEmpty()) {
            holder.tvDia.setBackgroundColor(Color.WHITE)
        } else {
            holder.tvDia.setBackgroundColor(Color.YELLOW)
        }

        holder.itemView.setOnClickListener {
            // Mostrar eventos del día en un diálogo de detalle
            val eventDetailDialog = EventDetailDialog(holder.itemView.context, eventosDia)
            eventDetailDialog.show()
        }

        holder.itemView.setOnLongClickListener {
            // Mostrar diálogo para crear un evento en el día
            val addEventDialog = AddEventDialog(holder.itemView.context, dia)
            addEventDialog.show()
            true
        }
    }

    override fun getItemCount(): Int {
        return 31 // Actualiza esto con el número de días del mes actual
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDia: TextView = itemView.findViewById(R.id.tvDia)
    }
}
