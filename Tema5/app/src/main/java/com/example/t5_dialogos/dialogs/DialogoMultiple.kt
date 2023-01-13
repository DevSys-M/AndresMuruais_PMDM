package com.example.t5_dialogos.dialogs

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DialogoMultiple: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext());
        val elementos = arrayOf("Opcion 1", "Opcion 2", "Opcion 3")

        builder.setTitle("Selección multiple")

        builder.setMultiChoiceItems(elementos,null){ _, i,boolean ->
            Log.v("dialogos", "${i.toString()} ${boolean.toString()}")
        }

        builder.setPositiveButton("OK") { _, _ ->
            Log.v("dialogos", "realizar comunicacion")
            // se le comunique a la activity el numero de elementos que estan seleccionados
        }

        builder.setNeutralButton("Cerrar") { _, _ ->
            Log.v("dialogos", "cerrando boton")
        }

        return  builder.create()
    }
}