package com.example.demoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R

class AdaptadorCalendario(var context: Context, var days: List<String>, var events: List<String>) :
    RecyclerView.Adapter<AdaptadorCalendario.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_day, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val day = days[position]
        holder.bind(day, events.contains(day))
    }

    override fun getItemCount(): Int {
        return days.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDay: TextView = itemView.findViewById(R.id.tvDay)

        fun bind(day: String, hasEvent: Boolean) {
            tvDay.text = day

            if (hasEvent) {
                // Resaltar los días con eventos
                tvDay.setBackgroundResource(R.drawable.background_event_day)
            } else {
                // Restablecer el fondo de los días sin eventos
                tvDay.setBackgroundResource(0)
            }

            // Manejar el clic en un día del calendario
            itemView.setOnClickListener {
                // Aquí puedes realizar alguna acción cuando se hace clic en un día
                val clickedDay = days[adapterPosition]
                showEventDetailDialog(clickedDay)
            }
        }
        private fun showEventDetailDialog(day: String) {
            val eventDetails = "Detalles del evento para el día $day" // Obtén los detalles del evento para el día seleccionado

            // Aquí puedes abrir una actividad o un cuadro de diálogo para mostrar los detalles del evento
            val dialogBuilder = AlertDialog.Builder(context)
            dialogBuilder.setTitle("Detalles del evento")
            dialogBuilder.setMessage(eventDetails)
            dialogBuilder.setPositiveButton("Aceptar", null)
            dialogBuilder.show()
        }
    }
}