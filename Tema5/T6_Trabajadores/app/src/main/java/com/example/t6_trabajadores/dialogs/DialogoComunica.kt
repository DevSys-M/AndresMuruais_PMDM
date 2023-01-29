package com.example.t6_trabajadores.dialogs

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.t6_trabajadores.MainActivity
import com.example.t6_trabajadores.R
import com.example.t6_trabajadores.model.Trabajador

class DialogoComunica : DialogFragment() {
    private lateinit var vista: View
    private lateinit var textoNombre: TextView
    private lateinit var textoApellido: TextView
    private lateinit var textoCorreo: TextView
    private lateinit var textoEdad: TextView
    private lateinit var textoPuesto: TextView
    private lateinit var linear: LinearLayout

    private lateinit var trabajador: Trabajador

    companion object {
        fun newInstance(empleado: Trabajador): DialogoComunica {
            val dialogoComunica = DialogoComunica()
            val args = Bundle()
            args.putSerializable("trabajador", empleado)
            dialogoComunica.arguments = args
            return dialogoComunica
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        vista = LayoutInflater.from(context).inflate(R.layout.item_dialog, null)
        trabajador = this.arguments?.getSerializable("trabajador") as Trabajador
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext());
        builder.setTitle("Empleado")

        builder.setView(vista)
        return builder.create()
    }

    override fun onStart() {
        super.onStart()
        textoNombre = vista.findViewById(R.id.fila_nombre)
        textoApellido = vista.findViewById(R.id.fila_apellido)
        textoCorreo = vista.findViewById(R.id.fila_correo)
        textoEdad = vista.findViewById(R.id.fila_edad)
        textoPuesto = vista.findViewById(R.id.fila_puesto)
        linear = vista.findViewById(R.id.linear)
    }

    override fun onResume() {
        super.onResume()
        textoNombre.text = trabajador.nombre
        textoApellido.text = trabajador.apellidos
        textoCorreo.text = trabajador.correo
        textoEdad.text = trabajador.edad
        textoPuesto.text = trabajador.puesto
        linear.setOnClickListener {
            dismiss()
        }

    }
}



