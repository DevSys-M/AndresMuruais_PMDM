package com.example.t5_dialogos.dialogs

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DialogoMultiple : DialogFragment() {

    lateinit var selecciones: ArrayList<String>

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        selecciones = ArrayList()

        val builder = AlertDialog.Builder(requireContext());
        val elementos = arrayOf("Opcion 1", "Opcion 2", "Opcion 3")

        builder.setTitle("Selección multiple")
        builder.setMultiChoiceItems(
            elementos,
            null,
            DialogInterface.OnMultiChoiceClickListener { _, i, boolean ->
                Log.v("dialogos", "${i.toString()} ${boolean.toString()}")
                if (boolean) {
                    selecciones.add(elementos[i])
                } else {
                    //eliminar de la lista
                    selecciones.remove(elementos[i])
                }
            })


        // se le comunique a la activity el numero de elementos que estan seleccionados
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { _, i ->
            //Log.v("dialogos", "realizar comunicacion")
            selecciones.forEach { Log.v("dialogo", it) }
        })

        builder.setNeutralButton("Cerrar") { _, _ ->
            Log.v("dialogos", "cerrando boton")
        }

        return builder.create()
    }
}