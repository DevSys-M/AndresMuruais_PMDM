package com.example.t3_hobbies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.t3_hobbies.R
import com.example.t3_hobbies.model.Hobbies

class AdapterHobbies(private var lista: ArrayList<Hobbies>,private var context: Context): BaseAdapter() {
    override fun getCount(): Int {
        return lista.size
    }

    override fun getItem(p0: Int): Any {
       return lista.get(p0)
    }

    override fun getItemId(p0: Int): Long {
       return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view: View = LayoutInflater.from(context).inflate(R.layout.list_item, p2, false)

        var hobbiesActual: Hobbies = lista.get(p0)

        var imagenFila: ImageView = view.findViewById(R.id.imagen_fila)
        var textoFilaNombre: TextView = view.findViewById(R.id.texto_fila_nombre)
        var textoFilaDetalle: TextView = view.findViewById(R.id.texto_fila_detalle)

        if (hobbiesActual.categoria == "Futbol") {
            if (hobbiesActual.nombre == "Messi") {
                imagenFila.setImageResource(R.drawable.messi)
            } else if (hobbiesActual.nombre == "Maradona") {
                imagenFila.setImageResource(R.drawable.maradona)
            } else if (hobbiesActual.nombre == "Ronaldo") {
                imagenFila.setImageResource(R.drawable.ronaldo)
            } else if (hobbiesActual.nombre == "Zidane") {
                imagenFila.setImageResource(R.drawable.zidane)
            }
        }
        else if (hobbiesActual.categoria == "Juegos"){
            if (hobbiesActual.nombre == "Metal Gear") {
                imagenFila.setImageResource(R.drawable.metal)
            } else if (hobbiesActual.nombre == "Gran Turismo") {
                imagenFila.setImageResource(R.drawable.gt)
            } else if (hobbiesActual.nombre == "God Of War") {
                imagenFila.setImageResource(R.drawable.god)
            } else if (hobbiesActual.nombre == "Final Fantasy X") {
                imagenFila.setImageResource(R.drawable.ffx)
            }
        }
        else if (hobbiesActual.categoria == "Series"){
            if (hobbiesActual.nombre == "Stranger Things") {
                imagenFila.setImageResource(R.drawable.stranger)
            } else if (hobbiesActual.nombre == "Stranger Things") {
                imagenFila.setImageResource(R.drawable.tronos)
            } else if (hobbiesActual.nombre == "Lost") {
                imagenFila.setImageResource(R.drawable.lost)
            } else if (hobbiesActual.nombre == "La casa de papel") {
                imagenFila.setImageResource(R.drawable.papel)
            }
        }

        textoFilaNombre.text = hobbiesActual.nombre;
        textoFilaDetalle.text = hobbiesActual.detalle;

        return view;
    }
}