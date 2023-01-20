package com.example.t5_dialogos.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.t5_dialogos.model.Usuario

class DialogoComunica: DialogFragment() {

    lateinit var nombre: String
    lateinit var apellido: String


    companion object{
        fun newInstance(nombre: String, apellido: String): DialogoComunica{
            val dialogoComunica = DialogoComunica()
            val bundle = Bundle()
            bundle.putString("nombre",nombre)
            bundle.putString("apellido",apellido)
            dialogoComunica.arguments = bundle
            return dialogoComunica
        }
        // si se pone newInstance
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        nombre = this.arguments?.getString("nombre")?: "asadsa"
        apellido = this.arguments?.getString("apellidos")?: "asadsa"

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // recueprar el nombre y ponerlo como titulo
        // recueprar el nombre y apelludo y ponerlo como mensaje

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Bienvenido"+nombre)
        builder.setMessage("Bienvenido $nombre $apellido este es tu primer cuadro de dialgo")

        return builder.create()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }



}