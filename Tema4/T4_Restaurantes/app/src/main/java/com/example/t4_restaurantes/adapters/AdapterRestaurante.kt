package com.example.t4_restaurantes.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.t4_restaurantes.R
import com.example.t4_restaurantes.model.Restaurante

class AdapterRestaurante(var context: Context,var lista: ArrayList<Restaurante>): RecyclerView.Adapter<AdapterRestaurante.MyHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val restaurante = lista.get(position)
        holder.imagen.setImageResource(restaurante.imagen)
        holder.nombre.setText(restaurante.nombre)
        holder.puntuacion.setText("Puntuacion: " + restaurante.valoracion)
    }

    override fun getItemCount(): Int {
       return lista.size
    }

    inner class MyHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var imagen: ImageView
        var nombre: TextView
        var puntuacion: TextView

        init {
            imagen = itemView.findViewById(R.id.imagen_holder)
            nombre = itemView.findViewById(R.id.nombre_holder)
            puntuacion= itemView.findViewById(R.id.puntuacion_holder)
        }

    }
}