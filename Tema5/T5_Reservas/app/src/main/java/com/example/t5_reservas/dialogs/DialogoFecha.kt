package com.example.t5_reservas.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.os.Message
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.DialogFragment
import java.util.*

class DialogoFecha: DialogFragment() {

    var anio = Calendar.getInstance().get(Calendar.YEAR)
    var mes = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    var dia = Calendar.getInstance().get(Calendar.DATE)

    private  var anioRecuperado: Int =0
    private var mesRecuperado: Int = 0
    private  var diaRecuperado: Int = 0
    companion object{
        fun newInstance(diaRecuperado: Int, mesRecuperado: Int, anioRecuperado: Int
        ):DialogoFecha {
            var dialogoFecha = DialogoFecha()
            val args = Bundle()
            args.putInt("dia",diaRecuperado)
            args.putInt("mes",mesRecuperado)
            args.putInt("anio",anioRecuperado)
            dialogoFecha.arguments = args
            return dialogoFecha
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        diaRecuperado = this.arguments?.getInt("dia")?:0
        mesRecuperado = this.arguments?.getInt("mes")?:0
        mesRecuperado = this.arguments?.getInt("anio")?:0

        if(diaRecuperado > -1){

            val dialogoHora = DatePickerDialog(requireContext(),activity as DatePickerDialog.OnDateSetListener,anio,mes,dia)
            return dialogoHora
        }else{
            val dialogoHora = DatePickerDialog(requireContext(),activity as DatePickerDialog.OnDateSetListener,diaRecuperado,mesRecuperado,mesRecuperado)
            return dialogoHora
        }



    }


}