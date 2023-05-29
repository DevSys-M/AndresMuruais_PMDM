package com.example.calendarapp

import android.icu.text.SimpleDateFormat


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormatSymbols
import java.util.*

class MainActivity : AppCompatActivity(), CalendarAdapter.OnDayClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var calendarAdapter: CalendarAdapter
    private lateinit var tvMonthYear: TextView

    private var viewMode = ViewMode.MONTHS

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
            R.id.menu_next_month -> {
                showNextMonth()
                true
            }
            R.id.menu_months -> {
                setViewMode(ViewMode.MONTHS)
                true
            }
            R.id.menu_weeks -> {
                setViewMode(ViewMode.WEEKS)
                true
            }
            R.id.menu_days -> {
                setViewMode(ViewMode.DAYS)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDayClick(day: Int) {
            val dialog = DialogoEventDetail.newInstance(day, emptyList())
            dialog.show(supportFragmentManager, "EventDetailDialog")
    }

    override fun onDayLongClick(day: Int) {
        val dialog = DialogoAddEvent.newInstance(day)
        dialog.show(supportFragmentManager, "AddEventDialog")
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
            ViewMode.WEEKS -> getWeekList(currentYear, currentMonth)
            ViewMode.DAYS -> getDayList(currentYear, currentMonth)
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

    private fun getWeekList(year: Int, month: Int): List<String> {
        val weekList = mutableListOf<String>()

        val calendar = Calendar.getInstance()
        calendar.set(year, month, 1)

        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        var startDay = 1
        var endDay = 7 - firstDayOfWeek + 1

        while (startDay <= maxDay) {
            val weekRange = "$startDay-$endDay"
            weekList.add(weekRange)

            startDay = endDay + 1
            endDay = startDay + 6

            if (endDay > maxDay)
                endDay = maxDay
        }

        return weekList
    }

    private fun getDayList(year: Int, month: Int): List<String> {
        val dayList = mutableListOf<String>()

        val calendar = Calendar.getInstance()
        calendar.set(year, month, 1)

        val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        for (i in 1..maxDay) {
            dayList.add(i.toString())
        }

        return dayList
    }

    private enum class ViewMode {
        MONTHS, WEEKS, DAYS
    }
}
