package com.example.t4_coches_ret.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.t4_coches_ret.R
import com.example.t4_coches_ret.model.Coche

class AdaptadorCoche(var context: Context, var lista: ArrayList<Coche>) : RecyclerView.Adapter<AdaptadorCoche.MyHolder>() {

    // 1. Funciones vacías: en el origen de los datos crear una variable de tipo funcion
    var funcionComunicar: ((coche: Coche)->Unit)? = null

    // TODO 2. Creo variable de la interfaz
    // private lateinit var listener: TIPO DE LA INTERFAZ

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imagen: ImageView
        var modelo: TextView
        var boton: Button

        init {
            imagen = itemView.findViewById(R.id.imagen_item)
            modelo = itemView.findViewById(R.id.modelo_item)
            boton = itemView.findViewById(R.id.boton_item)
        }
    }

    fun cambiarLista(listaNueva: ArrayList<Coche>){
        this.lista = listaNueva;
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val coche = lista.get(position)
        holder.modelo.text = coche.modelo
        holder.imagen.setImageResource(coche.imagen)
        holder.boton.setOnClickListener {
            // Funcion vacia:  comunicacion el coche seleccionado a la activity
            funcionComunicar?.invoke(coche)

            // TODO 2. utilizo el metodo de la interfaz onMetodoSelected(dato a comunicar)

        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    // TODO 1. Creo interfaz con metodo onMetodoSelected(dato a comunicar)
}