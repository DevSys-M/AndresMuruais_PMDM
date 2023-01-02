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

class AdapterHobbies(private var lista: ArrayList<Hobbies>, private var context: Context) :
    BaseAdapter() {
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

        if (hobbiesActual.getCategoria() == "Futbol") {
            if (hobbiesActual.getNombre() == "Messi") {
                imagenFila.setImageResource(R.drawable.messi)
            } else if (hobbiesActual.getNombre() == "Maradona") {
                imagenFila.setImageResource(R.drawable.maradona)
            } else if (hobbiesActual.getNombre() == "Ronaldo") {
                imagenFila.setImageResource(R.drawable.ronaldo)
            } else if (hobbiesActual.getNombre() == "Zidane") {
                imagenFila.setImageResource(R.drawable.zidane)
            }
        } else if (hobbiesActual.getCategoria() == "Juegos") {
            if (hobbiesActual.getNombre() == "Metal Gear") {
                imagenFila.setImageResource(R.drawable.metal)
            } else if (hobbiesActual.getNombre() == "Gran Turismo") {
                imagenFila.setImageResource(R.drawable.gt)
            } else if (hobbiesActual.getNombre() == "God Of War") {
                imagenFila.setImageResource(R.drawable.god)
            } else if (hobbiesActual.getNombre() == "Final Fantasy X") {
                imagenFila.setImageResource(R.drawable.ffx)
            }
        } else if (hobbiesActual.getCategoria() == "Series") {
            if (hobbiesActual.getNombre() == "Stranger Things") {
                imagenFila.setImageResource(R.drawable.stranger)
            } else if (hobbiesActual.getNombre() == "Stranger Things") {
                imagenFila.setImageResource(R.drawable.trono)
            } else if (hobbiesActual.getNombre() == "Lost") {
                imagenFila.setImageResource(R.drawable.lost)
            } else if (hobbiesActual.getNombre() == "La casa de papel") {
                imagenFila.setImageResource(R.drawable.papel)
            }
        }

        textoFilaNombre.text = hobbiesActual.getNombre();
        textoFilaDetalle.text = hobbiesActual.getDetalle();

        return view;
    }
}