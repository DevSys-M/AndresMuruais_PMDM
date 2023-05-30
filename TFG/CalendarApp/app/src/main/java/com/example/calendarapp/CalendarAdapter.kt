package com.example.calendarapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CalendarAdapter(
    private var dataList: List<String>,
    private val onDayClickListener: OnDayClickListener
) : RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {

    private val eventMap = mutableMapOf<Int, MutableList<String>>()
    var currentList: List<String> = dataList

    interface OnDayClickListener {
        fun onDayClick(day: Int)
        fun onDayLongClick(day: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calendar_day, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val day = dataList[position].toIntOrNull()
        if (day != null) {
            holder.dayTextView.text = day.toString()
            holder.itemView.setOnClickListener {
                onDayClickListener.onDayClick(day)
            }
            holder.itemView.setOnLongClickListener {
                onDayClickListener.onDayLongClick(day)
                true
            }

            val hasEvents = eventMap.containsKey(day)
            holder.dayTextView.visibility = View.VISIBLE
            holder.dayTextView.setBackgroundResource(
                if (hasEvents) R.drawable.background_yellow_circle else 0
            )
        } else {
            holder.dayTextView.text = ""
            holder.dayTextView.setBackgroundResource(0) // Elimina cualquier fondo
            holder.dayTextView.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateCurrentList(newList: List<String>) {
        currentList = newList
        notifyDataSetChanged()
    }

    fun updateList(newList: List<String>) {
        dataList = newList
        notifyDataSetChanged()
    }

    fun getItemAtPosition(position: Int): String {
        return dataList[position]
    }

    fun addEvent(day: Int, event: String) {
        val eventList = eventMap.getOrPut(day) { mutableListOf() }
        eventList.add(event)
        notifyItemChanged(day - 1)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayTextView: TextView = itemView.findViewById(R.id.dayTextView)
    }
}
