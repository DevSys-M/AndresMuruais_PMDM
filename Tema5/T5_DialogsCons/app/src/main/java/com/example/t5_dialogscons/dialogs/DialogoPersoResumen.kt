package com.example.t5_dialogscons.dialogs

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.t5_dialogscons.R

class DialogoPersoResumen:DialogFragment() {

    private lateinit var vista: View;
    private lateinit var hora: String
    private lateinit var minutos: String
    private lateinit var dia: String
    private lateinit var mes: String
    private lateinit var anio: String
    private lateinit var nombre: String
    private lateinit var arrayAsignaturas: ArrayList<String>
    private lateinit var nota: String

    private lateinit var textoNombre: TextView
    private lateinit var textoHora: TextView
    private lateinit var textoFecha: TextView
    private lateinit var textoAsignatura: TextView
    private lateinit var textoNota: TextView
    private lateinit var botonFin: Button

    companion object {
        fun newInstance(
            hora: String,
            minutos: String,
            dia: String,
            mes: String,
            anio: String,
            nombre: String,
            asignaturas: ArrayList<String>,
            nota: String
        ):DialogoPersoResumen {
            val dialogoPersoResumen = DialogoPersoResumen()
            val args = Bundle()
            args.putString("hora", hora)
            args.putString("minutos", minutos)
            args.putString("dia", dia)
            args.putString("mes", mes)
            args.putString("anio", anio)
            args.putString("nombre", nombre)
            args.putStringArrayList("asignaturas", asignaturas)
            args.putString("nota", nota)
            dialogoPersoResumen.arguments = args
            return dialogoPersoResumen
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
        arrayAsignaturas = (this.arguments?.getStringArray("asignaturas")?:"Asignatura") as ArrayList<String>
        nota = this.arguments?.getString("nota") ?: "0"
        vista = LayoutInflater.from(context).inflate(R.layout.item_resumen, null)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext());
        builder.setView(vista)
        return builder.create()
    }

    override fun onStart() {
        super.onStart()
        textoNombre = vista.findViewById(R.id.texto_nombre)
        textoFecha = vista.findViewById(R.id.texto_fecha)
        textoHora = vista.findViewById(R.id.texto_hora)
        textoAsignatura = vista.findViewById(R.id.texto_asignatura)
        textoNota = vista.findViewById(R.id.texto_media)
        botonFin = vista.findViewById(R.id.boton_fin)
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        botonFin.setOnClickListener{
            textoNombre.text= nombre
            textoHora.text = "${hora}:${minutos}"
            textoFecha.text = "${dia}/${mes}/${anio}"
            arrayAsignaturas.forEach { textoAsignatura.text = it }
            textoNota.text = nota
            dismiss()
        }

    }

}