package com.example.t5_dialogos.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.t5_dialogos.R

class DialogoPerso : DialogFragment() {

    lateinit var vista: View;
    lateinit var editNombre: EditText
    lateinit var editPass: EditText
    lateinit var checkRecord: CheckBox
    lateinit var botonLogin: Button

    override fun onAttach(context: Context) {
        super.onAttach(context)
        vista = LayoutInflater.from(context).inflate(R.layout.dialogo_login, null)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext());

        builder.setView(vista)

        return builder.create()
    }

    override fun onStart() {
        super.onStart()
        editNombre = vista.findViewById(R.id.edit_nombre)
        editPass = vista.findViewById(R.id.edit_pass)
        botonLogin = vista.findViewById(R.id.boton_login)
        checkRecord = vista.findViewById(R.id.checkbox)
    }

    override fun onResume() {
        super.onResume()
        botonLogin.setOnClickListener {
            // capturo lo que hay en los edit
            //ejecuto interfaz de callback y llevo los datos al activity
        }
    }
}