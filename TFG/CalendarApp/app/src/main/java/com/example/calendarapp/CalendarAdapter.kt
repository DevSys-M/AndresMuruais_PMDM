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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_day, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val day = dataList[position]
        holder.bind(day)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateList(newDataList: List<String>) {
        dataList = newDataList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {
        private val tvDay: TextView = itemView.findViewById(R.id.dayTextView)

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        fun bind(day: String) {
            tvDay.text = day
        }

        override fun onClick(view: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val clickedDay = dataList[position].toInt()
                onDayClickListener.onDayClick(clickedDay)
            }
        }

        override fun onLongClick(view: View): Boolean {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val clickedDay = dataList[position].toInt()
                onDayClickListener.onDayLongClick(clickedDay)
                return true
            }
            return false
        }
    }

    interface OnDayClickListener {
        fun onDayClick(day: Int)
        fun onDayLongClick(day: Int)
    }
}
