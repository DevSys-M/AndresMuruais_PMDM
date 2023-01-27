package com.example.t5_dialogscons.dialogs

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DialogoMultiple: DialogFragment() {

    private lateinit var selecciones: ArrayList<String>

    private lateinit var listener: OnMultipleListener


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        listener = requireContext() as OnMultipleListener
        selecciones = ArrayList()

        val builder = AlertDialog.Builder(requireContext())
        val elementos = arrayOf(" PMDM", "DI", "AD", "SGE", "EIE", "ING")

        builder.setTitle("Selecion Multiple")
        //builder.setMessage("¿De cuantas asignaturas te has evaluado?")

        builder.setMultiChoiceItems(elementos,null, DialogInterface.OnMultiChoiceClickListener { _, which, isChecked ->
            if(isChecked){
                selecciones.add(elementos[which])
            } else{
                selecciones.remove(elementos[which])
            }
        })
         builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener { _, _ ->
                listener.onMultipleSelected(selecciones)
         })

            return builder.create()
    }
    interface OnMultipleListener{
        fun onMultipleSelected(lista: ArrayList<String>)
    }
}