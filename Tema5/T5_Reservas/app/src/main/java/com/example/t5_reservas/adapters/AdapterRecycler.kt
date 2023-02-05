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
import com.example.t5_reservas.model.Reservas

class AdapterRecycler(var lista: ArrayList<Reservas>, var context: Context) :
    RecyclerView.Adapter<AdapterRecycler.MyHolder>() {


    private var listener: OnRecyclerListener

    init {
        listener = context as OnRecyclerListener
    }


    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imagenOrigen: ImageView
        var imagenDestino: ImageView
        var textoOrigenDestino: TextView
        var bontonDetalle: Button

        init {
            imagenOrigen = itemView.findViewById(R.id.imagen_origen)
            imagenDestino = itemView.findViewById(R.id.imagen_destino)
            textoOrigenDestino = itemView.findViewById(R.id.texto_item_recycler)
            bontonDetalle = itemView.findViewById(R.id.boton_detalles)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false)
        return MyHolder(view)

    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val reservas = lista[position]
        holder.imagenOrigen.setImageResource(reservas.imagen0)
        holder.imagenDestino.setImageResource(reservas.imagenD)
        holder.textoOrigenDestino.text = "${reservas.nombreO}-${reservas.nombreD}"

        holder.bontonDetalle.setOnClickListener {
            listener.onRecyclerSelected(reservas)
        }
    }

    fun addReserva(reservas: Reservas){
        this.lista.add(reservas)
        notifyItemInserted(lista.size-1)
    }


    interface OnRecyclerListener {
        fun onRecyclerSelected(reservas: Reservas)
    }


}