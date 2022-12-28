package com.example.t3_spinner.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.t3_spinner.R
import com.example.t3_spinner.model.Pais

class AdapterPais(private var listaDatos: ArrayList<Pais>, private var contexto : Context): BaseAdapter() {
    override fun getCount(): Int {
        //nuemro de filas  --> nº elementos en el **lista**
        return listaDatos.size;
    }

    override fun getItem(p0: Int): Pais {
        // el elemento que este en la posicion p0 de la **lista**
        return listaDatos.get(p0);
    }

    override fun getItemId(p0: Int): Long {
        // el id del elemento p0 de la **lista** -->  indice (p0 as Long)
        return p0.toLong();
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        // retorna la fila en la posicion p0 con las datos ya puestos de la **lista** de la posicion p0
        var view: View = LayoutInflater.from(contexto).inflate(R.layout.spinner_item, p2,false);

        //Asociar los datos del pais en la posicion p0
        var paisActual = listaDatos.get(p0);
        // En los views del xml creado
        var imagenFila: ImageView = view.findViewById(R.id.imagen_fila);
        var textoFila: TextView = view.findViewById(R.id.texto_fila);

        imagenFila.setImageResource(paisActual.getImagen());
        textoFila.text = paisActual.getNombre();

        return  view;
    }

}