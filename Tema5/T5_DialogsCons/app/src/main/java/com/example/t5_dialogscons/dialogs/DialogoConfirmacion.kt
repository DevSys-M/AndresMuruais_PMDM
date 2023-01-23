package com.example.t5_dialogscons.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DialogoConfirmacion : DialogFragment() {

    private lateinit var listener: OnDialogoListener

    private lateinit var hora: String
    private lateinit var minutos: String
    private lateinit var dia: String
    private lateinit var mes: String
    private lateinit var anio: String
    private lateinit var nombre: String


    companion object {
        fun newInstance(
            hora: String,
            minutos: String,
            dia: String,
            mes: String,
            anio: String,
            nombre: String
        ): DialogoConfirmacion {
            val dialogoConfirmacion = DialogoConfirmacion()
            val args = Bundle()
            args.putString("hora", hora)
            args.putString("minutos", minutos)
            args.putString("dia", dia)
            args.putString("mes", mes)
            args.putString("anio", anio)
            args.putString("nombre", nombre)
            dialogoConfirmacion.arguments = args
            return dialogoConfirmacion
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hora = this.arguments?.getString("hora")?:"HH"
        minutos = this.arguments?.getString("minutos") ?: "MM"
        dia = this.arguments?.getString("dia") ?: "D"
        mes = this.arguments?.getString("mes") ?: "M"
        anio = this.arguments?.getString("anio") ?: "Y"
        nombre = this.arguments?.getString("nombre") ?: "NOMBRE"

        this.listener = context as OnDialogoListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder = AlertDialog.Builder(requireContext())


        builder.setTitle("Continuar")
        builder.setMessage(
            "Buenos días ${nombre}, vas a registrar una respuesta el ${dia}/${mes}/${anio}  a las ${hora}:${minutos}." +
                    " ¿Estás seguro que quieres continuar?"
        )

        builder.setPositiveButton("ACEPTAR", DialogInterface.OnClickListener { _, _ ->
            listener.onDialogoSelected(true)
        })
        builder.setNegativeButton("CANCELAR", DialogInterface.OnClickListener { _, _ ->

            listener.onDialogoSelected(false)
        })

        return builder.create()
    }

    interface OnDialogoListener {
        fun onDialogoSelected(boolean: Boolean)
    }
}