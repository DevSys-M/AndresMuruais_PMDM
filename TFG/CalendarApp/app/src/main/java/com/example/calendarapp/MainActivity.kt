package com.example.calendarapp

import androidx.appcompat.app.AppCompatActivity
import android.widget.CalendarView
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            // Maneja el evento de selección de fecha aquí
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            // Realiza las acciones deseadas con la fecha seleccionada
        }
    }
}