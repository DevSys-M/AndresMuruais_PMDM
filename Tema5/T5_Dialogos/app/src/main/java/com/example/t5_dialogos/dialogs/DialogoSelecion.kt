package com.example.t5_dialogos.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment

class DialogoSelecion: DialogFragment() {

    private lateinit var listener: onDialogoListaListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        this.listener = context as onDialogoListaListener

        val builder = AlertDialog.Builder(requireContext())
        val elementos = arrayOf("Opcion 1","Opcion 2","Opcion 3","Opcion 4")

        builder.setTitle("Selecciona una opcion")

        //builder.setMessage("no puede tener"

        builder.setItems(elementos,DialogInterface.OnClickListener { _, i ->
            Log.v("dialogos","Pulsado ${i}")
            listener.onDialogoListaSelected("${elementos[i]}")
        })

        return builder.create();
    }

    interface  onDialogoListaListener{
        fun onDialogoListaSelected(mensaje: String)
    }
}