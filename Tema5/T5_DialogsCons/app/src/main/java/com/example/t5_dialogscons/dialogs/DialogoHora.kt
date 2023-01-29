package com.example.t5_dialogscons.dialogs

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.t5_dialogscons.R
import java.util.*

class DialogoHora: DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val calendario: Calendar = Calendar.getInstance();
        val hora = calendario.get(Calendar.HOUR)
        val minutos = calendario.get(Calendar.MINUTE)

        val dialogoHora = TimePickerDialog(requireContext(),requireContext() as TimePickerDialog.OnTimeSetListener,hora,minutos,false)
        return dialogoHora
    }
}