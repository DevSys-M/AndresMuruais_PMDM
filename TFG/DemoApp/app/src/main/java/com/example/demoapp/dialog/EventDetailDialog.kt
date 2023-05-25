package com.example.demoapp.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.demoapp.R

class EventDetailDialog(context: Context, private val eventosDia: List<String>?) : Dialog(context) {
    private lateinit var tvEventos: TextView
    private lateinit var btnCerrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_event_detail)

        tvEventos = findViewById(R.id.textViewEvents)
        btnCerrar = findViewById(R.id.buttonOk)

        if (eventosDia.isNullOrEmpty()) {
            tvEventos.text = "No hay eventos para este día."
        } else {
            val eventos = eventosDia.joinToString("\n")
            tvEventos.text = eventos
        }

        btnCerrar.setOnClickListener {
            dismiss()
        }
    }
}
