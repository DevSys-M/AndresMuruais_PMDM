package com.example.demoapp.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.demoapp.R

class AddEventDialog(context: Context, private val dia: Int) : Dialog(context) {
    private lateinit var etEvento: EditText
    private lateinit var btnAgregar: Button

    private var eventAddedListener: ((Int, String) -> Unit)? = null

    fun setOnEventAddedListener(listener: (Int, String) -> Unit) {
        eventAddedListener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_event)

        etEvento = findViewById(R.id.editTextEvent)
        btnAgregar = findViewById(R.id.buttonAdd)

        btnAgregar.setOnClickListener {
            val evento = etEvento.text.toString().trim()
            if (evento.isNotEmpty()) {
                eventAddedListener?.invoke(dia, evento)
                dismiss()
            }
        }
    }
}
