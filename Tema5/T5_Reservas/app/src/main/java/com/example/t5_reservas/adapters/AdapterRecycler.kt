package com.example.t5_reservas.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.t5_reservas.R
import com.example.t5_reservas.model.Ciudades

class AdapterRecycler(var lista:ArrayList<Ciudades>,var context: Context):RecyclerView.Adapter<AdapterRecycler.MyHolder>() {

    private  var listener: OnRecyclerListener

    init {
        listener = context as OnRecyclerListener
    }

    inner class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        lateinit var imagenOrigen: ImageView
        lateinit var imagenDestino: ImageView
        lateinit var textoOrigenDestino: TextView
        lateinit var bontDetalle: Button

        init {
            imagenOrigen = itemView.findViewById(R.id.imagen_origen)
            imagenDestino = itemView.findViewById(R.id.imagen_destino)
            textoOrigenDestino = itemView.findViewById(R.id.texto_item_recycler)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recycler,parent,false)
        return MyHolder(view)

    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val ciudad = lista[position]
        holder.imagenOrigen.setImageResource(ciudad.imagen)
        holder.imagenDestino.setImageResource(ciudad.imagen)
        holder.textoOrigenDestino.text = "${ciudad.texto}-${ciudad.texto}"

        holder.bontDetalle.setOnClickListener {
            listener.onRecyclerSelected(ciudad)
        }
    }
    interface OnRecyclerListener{
        fun onRecyclerSelected(ciudad: Ciudades)
    }
}