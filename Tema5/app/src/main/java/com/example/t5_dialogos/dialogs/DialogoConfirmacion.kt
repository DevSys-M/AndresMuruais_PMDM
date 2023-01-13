package com.example.t5_dialogos.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.t5_dialogos.R

class DialogoConfirmacion: DialogFragment() {

    private lateinit var contexto: Context
    //interface
    private lateinit var listener: OnDialogoListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.contexto = context
        //interface
        this.listener = context as OnDialogoListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        //AlertDialog.Builder
        //var builder = AlertDialog.Builder(requierContext())
        var builder = AlertDialog.Builder(contexto)
        builder.setTitle("Continuar")
        //builder.setTitle(getString(R.string.titulo_dialogo_confirm))
        builder.setMessage("¿Estas Seguro que quieres continuar?")

        // botones -- positivo negativo neutral

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { _,_ ->
            Log.v("dialogo","PULSADO OK")
            listener.onDialogoSelected("OK")
        })
        builder.setNegativeButton("CANCELAR", DialogInterface.OnClickListener { _,_ ->
            Log.v("dialogo","PULSADO CANCELAR")
            listener.onDialogoSelected("CANCELAR")
        })
        builder.setNeutralButton("SALIR", DialogInterface.OnClickListener { _,_ ->
            Log.v("dialogo","PULSADO SALIR")
            listener.onDialogoSelected("SALIR")
        })

        return builder.create()
    }

    interface OnDialogoListener{
        fun onDialogoSelected(mensaje:String)
    }
}