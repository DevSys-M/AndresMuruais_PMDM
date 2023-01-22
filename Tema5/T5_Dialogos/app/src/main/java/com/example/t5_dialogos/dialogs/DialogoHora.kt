package com.example.t5_dialogos.dialogs

import android.app.Dialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.t5_dialogos.R
import java.util.Calendar

class DialogoHora: DialogFragment() {



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val calendario: Calendar = Calendar.getInstance();
        val hora = calendario.get(Calendar.HOUR)
        val minutos = calendario.get(Calendar.MINUTE)
        val dialogoHora = TimePickerDialog(requireContext(),requireContext() as OnTimeSetListener,hora,minutos,true)
        TimePickerDialog( requireContext(),
            R.style.CuadroColor,requireContext() as OnTimeSetListener,hora,minutos,true)
        return dialogoHora
    }


}