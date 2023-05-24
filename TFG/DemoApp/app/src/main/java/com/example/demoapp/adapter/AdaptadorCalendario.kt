package com.example.demoapp.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.databinding.ItemCalendarioBinding

class AdaptadorCalendario(
    private val context: Context,
    private val onDateClickListener: OnDateClickListener
) : RecyclerView.Adapter<AdaptadorCalendario.ViewHolder>() {

    private val dates: MutableList<String> = mutableListOf()
    private val events: MutableMap<String, String> = mutableMapOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCalendarioBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date = dates[position]
        val eventText = events[date]

        holder.bind(date, eventText)

        holder.itemView.setOnClickListener {
            onDateClickListener.onDateClick(date)
        }

        holder.itemView.setOnLongClickListener {
            if (eventText != null) {
                val backgroundColor = Color.parseColor("#FFFF00")
                holder.itemView.setBackgroundColor(backgroundColor)
                true
            } else {
                false
            }
        }
    }

    override fun getItemCount(): Int {
        return dates.size
    }

    fun addDates(datesList: List<String>) {
        dates.clear()
        dates.addAll(datesList)
        notifyDataSetChanged()
    }

    fun addEvent(date: String, eventText: String) {
        events[date] = eventText
        notifyDataSetChanged()
    }

    fun getEvent(date: String): String? {
        return events[date]
    }

    fun getAllEvents(): Map<String, String> {
        return events
    }

    interface OnDateClickListener {
        fun onDateClick(date: String)
    }

    inner class ViewHolder(private val binding: ItemCalendarioBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(date: String, eventText: String?) {
            binding.textDate.text = date
            if (eventText != null) {
                binding.textEvent.visibility = View.VISIBLE
                binding.textEvent.text = eventText
            } else {
                binding.textEvent.visibility = View.GONE
            }
        }
    }
}

