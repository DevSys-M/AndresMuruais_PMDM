package com.example.t5_dialogscons.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.t5_dialogscons.R
import java.util.*

class DialogoFecha: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val calendario: Calendar = Calendar.getInstance();
        val dia = calendario.get(Calendar.DAY_OF_MONTH)
        val mes = calendario.get(Calendar.MONTH)
        val anio = calendario.get(Calendar.YEAR)
        val dialogoFecha = DatePickerDialog(requireContext(),requireContext() as DatePickerDialog.OnDateSetListener,anio,mes,dia)
        return dialogoFecha
    }

}