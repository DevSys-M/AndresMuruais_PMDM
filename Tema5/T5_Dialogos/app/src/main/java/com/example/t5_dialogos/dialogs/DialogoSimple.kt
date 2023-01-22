package com.example.t5_dialogos.dialogs

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DialogoSimple: DialogFragment() {

    private lateinit var listener: OnDialogoSimpleListener
    private  var elementoSeleccionado: String? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        this.listener= context as OnDialogoSimpleListener

        val builder = AlertDialog.Builder(requireContext())
        val elementos = arrayOf("Opcion 1","Opcion 2","Opcion 3","Opcion 4")

        builder.setTitle("Simple")
        builder.setSingleChoiceItems(elementos,-1) { _, i ->
            elementoSeleccionado = elementos[i]
        }
        builder.setPositiveButton("OK",DialogInterface.OnClickListener { _,_ ->
            Log.v("dialogo", "OK")
            listener.onDialogoSimpleSelected("OK")
        })
        builder.setNeutralButton("SALIR", DialogInterface.OnClickListener { _,_ ->
            Log.v("dialogo","PULSADO SALIR")
            listener.onDialogoSimpleSelected("SALIR")
        })

        return builder.create();
    }

    interface OnDialogoSimpleListener{
        fun onDialogoSimpleSelected(mensaje: String)
    }
}