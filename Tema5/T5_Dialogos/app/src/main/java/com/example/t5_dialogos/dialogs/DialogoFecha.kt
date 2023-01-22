package com.example.t5_dialogos.dialogs

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class DialogoFecha: DialogFragment() {



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val calendario: Calendar = Calendar.getInstance();
        val dia = calendario.get(Calendar.DAY_OF_MONTH)
        val mes = calendario.get(Calendar.MONTH)
        val anio = calendario.get(Calendar.YEAR)
        val dialogoFecha = DatePickerDialog(requireContext(),requireContext() as OnDateSetListener,anio,mes,dia)
        return dialogoFecha
    }


}