package com.example.calendarapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormatSymbols
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var calendarAdapter: CalendarAdapter

    private var viewMode = ViewMode.MONTHS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 7)

        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)

        val monthList = getMonthList(currentYear, currentMonth)
        calendarAdapter = CalendarAdapter(monthList)
        recyclerView.adapter = calendarAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
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
        // Implement logic to get a list of weeks in the selected month
        return emptyList()
    }

    private fun getDayList(year: Int, month: Int): List<String> {
        // Implement logic to get a list of days in the selected month
        return emptyList()
    }

    private enum class ViewMode {
        MONTHS, WEEKS, DAYS
    }
}
