package com.example.t7_iniciofragment.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DialogoMensaje: DialogFragment() {

    lateinit var nombre: String


    companion object{
        fun newInstance(nombre: String, ): DialogoMensaje{
            val dialogoComunica = DialogoMensaje()
            val bundle = Bundle()
            bundle.putString("nombre",nombre)
            dialogoComunica.arguments = bundle
            return dialogoComunica
        }
        // si se pone newInstance
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        nombre = this.arguments?.getString("nombre")?: "asadsa"

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // recueprar el nombre y ponerlo como titulo
        // recueprar el nombre y apelludo y ponerlo como mensaje

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Bienvenido"+nombre)
        builder.setMessage("Primera app de fragmetns $nombre")

        return builder.create()
    }



}