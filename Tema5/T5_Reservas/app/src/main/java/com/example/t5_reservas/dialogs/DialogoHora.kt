package com.example.t5_reservas.dialogs

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class DialogoHora: DialogFragment() {
    val hora = Calendar.getInstance().get(Calendar.HOUR)
    val minutos = Calendar.getInstance().get(Calendar.MINUTE)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogo = TimePickerDialog(context, activity as TimePickerDialog.OnTimeSetListener, hora,minutos,true)
        return dialogo
    }
}