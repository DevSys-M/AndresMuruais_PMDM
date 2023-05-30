package com.example.calendarapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), CalendarAdapter.OnDayClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var calendarAdapter: CalendarAdapter
    private lateinit var tvMonthYear: TextView

    private var viewMode = ViewMode.MONTHS

    private val eventMap = mutableMapOf<Int, MutableList<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        tvMonthYear = findViewById(R.id.monthYearTextView)
        recyclerView.layoutManager = GridLayoutManager(this, 7)

        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)

        val monthList = getMonthList(currentYear, currentMonth)
        calendarAdapter = CalendarAdapter(monthList, this)
        recyclerView.adapter = calendarAdapter

        val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(calendar.time)
        tvMonthYear.text = formattedDate

        updateEventIndicators()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_previous_month -> {
                showPreviousMonth()
                true
            }
            R.id.menu_months -> {
                setViewMode(ViewMode.MONTHS)
                true
            }
            R.id.menu_next_month -> {
                showNextMonth()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDayClick(day: Int) {
        if (viewMode == ViewMode.MONTHS) {
            val dialog = DialogoEventDetail.newInstance(day, eventMap[day] ?: emptyList())
            dialog.show(supportFragmentManager, "EventDetailDialog")
        } else {
            val dialog = DialogoAddEvent.newInstance(day)
            dialog.setOnAddEventListener { event ->
                addEvent(day, event)
            }
            dialog.show(supportFragmentManager, "AddEventDialog")
        }
    }

    override fun onDayLongClick(day: Int) {
        val dialog = DialogoAddEvent.newInstance(day)
        dialog.setOnAddEventListener { event ->
            addEvent(day, event)
        }
        dialog.show(supportFragmentManager, "AddEventDialog")
    }

    private fun updateEventIndicators() {
        val currentList = calendarAdapter.currentList
        val itemCount = calendarAdapter.itemCount

        for (i in 0 until itemCount) {
            val day = currentList[i].toIntOrNull()
            val hasEvents = day != null && eventMap.containsKey(day)

            val viewHolder =
                recyclerView.findViewHolderForAdapterPosition(i) as? CalendarAdapter.ViewHolder

            viewHolder?.dayTextView?.setBackgroundResource(
                if (hasEvents) R.drawable.background_yellow_circle else 0
            )
        }
    }


    private fun addEvent(day: Int, event: String) {
        val eventList = eventMap.getOrDefault(day, mutableListOf())
        eventList.add(event)
        eventMap[day] = eventList
        updateEventIndicators()
    }


    private fun showPreviousMonth() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -1)
        val previousYear = calendar.get(Calendar.YEAR)
        val previousMonth = calendar.get(Calendar.MONTH)
        val monthList = getMonthList(previousYear, previousMonth)
        calendarAdapter.updateList(monthList)

        val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(calendar.time)
        tvMonthYear.text = formattedDate
    }

    private fun showNextMonth() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, 1)
        val nextYear = calendar.get(Calendar.YEAR)
        val nextMonth = calendar.get(Calendar.MONTH)
        val monthList = getMonthList(nextYear, nextMonth)
        calendarAdapter.updateList(monthList)

        val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(calendar.time)
        tvMonthYear.text = formattedDate
    }

    private fun setViewMode(mode: ViewMode) {
        viewMode = mode
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)

        val list = when (mode) {
            ViewMode.MONTHS -> getMonthList(currentYear, currentMonth)
        }

        calendarAdapter.updateList(list)
    }

    private fun getMonthList(year: Int, month: Int): List<String> {
        val monthList = mutableListOf<String>()
        val calendar = Calendar.getInstance()
        calendar.set(year, month, 1)

        val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        val weekdays = DateFormatSymbols.getInstance().shortWeekdays
        for (i in 1 until firstDayOfWeek) {
            monthList.add("")
        }

        for (i in 1..maxDay) {
            monthList.add(i.toString())
        }

        return monthList
    }

    private enum class ViewMode {
        MONTHS
    }
}
