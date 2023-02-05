package com.example.t5_reservas.dialogs

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.t5_reservas.R
import com.example.t5_reservas.model.Reservas

class DialogoDetalles : DialogFragment() {
    private lateinit var vista: View;

    private lateinit var reservas: Reservas

    private lateinit var nombreO: TextView
    private lateinit var nombreD: TextView
    private lateinit var fecha: TextView
    private lateinit var hora: TextView
    private lateinit var linear: LinearLayout

    companion object {
        fun newInstance(
            reservas: Reservas
        ): DialogoDetalles {
            val args = Bundle()
            args.putSerializable("reserva", reservas)
            val fragment = DialogoDetalles()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        vista = LayoutInflater.from(context).inflate(R.layout.item_dialogo, null)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext());
        builder.setView(vista)
        return builder.create()
    }

    override fun onStart() {
        super.onStart()
        nombreO = vista.findViewById(R.id.texto_origen)
        nombreD = vista.findViewById(R.id.texto_destino)
        fecha = vista.findViewById(R.id.texto_fecha_inicio)
        hora = vista.findViewById(R.id.texto_fecha_inicio)
        linear = vista.findViewById(R.id.item_linear)
    }

    override fun onResume() {
        super.onResume()
        nombreO.text = reservas.nombreO
        nombreD.text = reservas.nombreD
        fecha.text = reservas.fecha
        hora.text = reservas.hora
        linear.setOnClickListener {
            dismiss()
        }
    }
}