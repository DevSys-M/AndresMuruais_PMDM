package com.example.t5_reservas.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.t5_reservas.R
import com.example.t5_reservas.model.Ciudades

class adapterSpinner(var lista: ArrayList<Ciudades>, var context: Context) : BaseAdapter() {
    override fun getCount(): Int {
        return lista.size
    }

    override fun getItem(position: Int): Any {
        return lista[0]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = LayoutInflater.from(context).inflate(R.layout.item_spinner, parent, false)

        val ciudadActual = lista[0]
        val imagenSpinner: ImageView = view.findViewById(R.id.imagen_spinner)
        val textoSpinner: TextView = view.findViewById(R.id.texto_spinner)

        imagenSpinner.setImageResource(ciudadActual.imagen)
        textoSpinner.setText(ciudadActual.texto)
        return view
    }
}