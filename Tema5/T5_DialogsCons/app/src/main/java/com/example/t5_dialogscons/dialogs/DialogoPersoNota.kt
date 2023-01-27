package com.example.t5_dialogscons.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.t5_dialogscons.R

class DialogoPersoNota : DialogFragment() {
    private lateinit var vista: View
    private lateinit var editNota: EditText
    private lateinit var botonAceptar: Button

    private lateinit var listener: OnNotaListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnNotaListener
        vista = LayoutInflater.from(context).inflate(R.layout.item_nota, null)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext());
        builder.setView(vista)
        return builder.create()
    }

    override fun onStart() {
        super.onStart()
        editNota = vista.findViewById(R.id.edit_nota)
        botonAceptar = vista.findViewById(R.id.boton_nota)
    }

    override fun onResume() {
        super.onResume()
        botonAceptar.setOnClickListener {
            listener.notaSelected(editNota.text.toString())
            dismiss()
        }
    }

    interface OnNotaListener {
        fun notaSelected(mensaje: String)
    }


}