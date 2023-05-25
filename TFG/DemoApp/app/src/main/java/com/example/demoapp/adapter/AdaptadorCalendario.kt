package com.example.demoapp.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R

class AdaptadorCalendario(
    private val context: Context,
    private val onDateClickListener: OnDateClickListener
) : RecyclerView.Adapter<AdaptadorCalendario.CalendarioViewHolder>() {

    private val eventList: MutableList<Pair<String, List<String>>> = mutableListOf()

    fun addEvent(date: String, event: String) {
        val existingEntry = eventList.find { it.first == date }
        if (existingEntry != null) {
            val updatedEvents = existingEntry.second.toMutableList().apply { add(event) }
            eventList.remove(existingEntry)
            eventList.add(date to updatedEvents)
        } else {
            eventList.add(date to listOf(event))
        }
        notifyDataSetChanged()
    }

    fun getAllEvents(): List<Pair<String, List<String>>> {
        return eventList.sortedBy { it.first }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarioViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_calendario, parent, false)
        return CalendarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarioViewHolder, position: Int) {
        val (date, events) = eventList[position]
        holder.bind(date, events)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    inner class CalendarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtDate: TextView = itemView.findViewById(R.id.txtDate)

        init {
            itemView.setOnClickListener {
                val (date, _) = eventList[adapterPosition]
                onDateClickListener.onDateClick(date)
            }
        }

        fun bind(date: String, events: List<String>) {
            txtDate.text = date

            if (events.isNotEmpty()) {
                txtDate.setTextColor(Color.YELLOW)
            } else {
                txtDate.setTextColor(Color.BLACK)
            }
        }
    }

    interface OnDateClickListener {
        fun onDateClick(date: String)
    }
}
