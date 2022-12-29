package com.example.t3_listas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.t3_listas.R
import com.example.t3_listas.model.Usuario

class AdapterUsuario(private var lista: ArrayList<Usuario>, private  var contexto: Context): BaseAdapter() {
    override fun getCount(): Int {
        return lista.size;
    }

    override fun getItem(p0: Int): Usuario {
        return lista.get(p0);
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong();
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view: View = LayoutInflater.from(contexto).inflate(R.layout.list_item,p2,false)

        var usuarioActual = lista.get(p0);

        var imagenFila: ImageView = view.findViewById(R.id.imagen_fila)
        var textoFila: TextView = view.findViewById(R.id.texto_fila)

        if(usuarioActual.genero == "masculino"){
            imagenFila.setImageResource(R.drawable.male);
        }else  if(usuarioActual.genero == "femenino"){
            imagenFila.setImageResource(R.drawable.female);
        }

        textoFila.text = usuarioActual.nombre;

        return view;
    }

}