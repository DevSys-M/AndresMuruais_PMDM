package com.example.t4_restaurantes.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.t4_restaurantes.R
import com.example.t4_restaurantes.model.Comida

class AdapterComida(private var listaDatos: ArrayList<Comida>,private var context: Context): BaseAdapter() {
    override fun getCount(): Int {
       return listaDatos.size
    }

    override fun getItem(p0: Int): Any {
       return listaDatos.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        var view: View = LayoutInflater.from(context).inflate(R.layout.item_spinner,p2,false)

        var comidaActual = listaDatos.get(p0)
        var imagenFila: ImageView = view.findViewById(R.id.imagen_spinner)
        var textoFila: TextView = view.findViewById(R.id.texto_spinner)

        imagenFila.setImageResource(comidaActual.getImagen())
        textoFila.setText(comidaActual.getNombre())

        return view
    }

}