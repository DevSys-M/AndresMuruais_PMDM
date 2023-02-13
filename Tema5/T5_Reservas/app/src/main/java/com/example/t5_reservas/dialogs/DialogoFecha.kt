package com.example.t5_reservas.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class DialogoFecha : DialogFragment() {


    private var dia: Int = 0
    private var mes: Int = 1
    private var anio: Int = 0

    companion object {
        fun newInstance(
            dia: Int, mes: Int, anio: Int
        ): DialogoFecha {
            var dialogoFecha = DialogoFecha()
            val args = Bundle()
            args.putInt("dia", dia)
            args.putInt("mes", mes)
            args.putInt("anio", anio)
            dialogoFecha.arguments = args
            return dialogoFecha
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        dia = c.get(Calendar.DAY_OF_MONTH)
        mes = c.get(Calendar.MONTH)
        anio = c.get(Calendar.YEAR)

        val dialogoFecha = DatePickerDialog(
            requireContext(),
            activity as DatePickerDialog.OnDateSetListener,
            anio,
            mes,
            dia
        )
        return dialogoFecha


    }
}