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

    private val eventMap: MutableMap<String, MutableList<String>> = mutableMapOf()

    fun addEvent(date: String, event: String) {
        if (eventMap.containsKey(date)) {
            eventMap[date]?.add(event)
        } else {
            eventMap[date] = mutableListOf(event)
        }
        notifyDataSetChanged()
    }

    fun getAllEvents(): Map<String, List<String>> {
        return eventMap
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarioViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_calendario, parent, false)
        return CalendarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarioViewHolder, position: Int) {
        val date = eventMap.keys.toList()[position]
        holder.bind(date, eventMap[date])
    }

    override fun getItemCount(): Int {
        return eventMap.size
    }

    inner class CalendarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtDate: TextView = itemView.findViewById(R.id.txtDate)

        init {
            itemView.setOnClickListener {
                val date = eventMap.keys.toList()[adapterPosition]
                onDateClickListener.onDateClick(date)
            }
        }

        fun bind(date: String, events: List<String>?) {
            txtDate.text = date

            if (events != null && events.isNotEmpty()) {
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
