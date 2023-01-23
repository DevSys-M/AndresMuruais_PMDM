package com.example.t5_dialogscons.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.t5_dialogscons.R
import com.example.t5_dialogscons.model.Usuario

class DialogoPerso: DialogFragment() {
    lateinit var vista: View
    lateinit var editNombre: EditText
    lateinit var editApellido: EditText
    lateinit var botonRegistrar: Button

    lateinit var listener: OnUsuarioListener


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnUsuarioListener
        vista = LayoutInflater.from(context).inflate(R.layout.item_dialog,null)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext());
        builder.setView(vista)
        return builder.create()
    }

    override fun onStart() {
        super.onStart()
        editNombre = vista.findViewById(R.id.edit_nombre)
        editApellido = vista.findViewById(R.id.edit_apellido)
        botonRegistrar = vista.findViewById(R.id.boton_registrar)
    }

    override fun onResume() {
        super.onResume()
        botonRegistrar.setOnClickListener{

            listener.usuarioSelected(Usuario(editNombre.text.toString(),editApellido.text.toString()))
            dismiss()

        }
    }

    interface OnUsuarioListener{
        fun usuarioSelected(usuario: Usuario)
    }
}