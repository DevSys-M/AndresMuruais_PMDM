package com.example.t4_conversor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.t4_conversor.model.Moneda
import com.example.t4_conversor.R
import com.google.android.material.snackbar.Snackbar

class AdapterMoneda(var context: Context, var lista: ArrayList<Moneda>) :
    RecyclerView.Adapter<AdapterMoneda.MyHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false)
        return MyHolder(view);
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val moneda = lista.get(position)
        if (moneda.getOrigen().equals("euros") || moneda.getDestino().equals("euros")) {
            holder.imagenOrigen.setImageResource(R.drawable.euro)
        } else if (moneda.getOrigen().equals("libra") || moneda.getDestino().equals("libra")) {
            holder.imagenOrigen.setImageResource(R.drawable.libra)
        } else if (moneda.getOrigen().equals("dolar") || moneda.getDestino().equals("dolar")) {
            holder.imagenOrigen.setImageResource(R.drawable.dollar)
        }
        holder.imagenOrigen.setOnClickListener {
            Snackbar.make(holder.imagenOrigen,"La cantidad origen es: ${lista.get(position).getTexto().toString()}",Snackbar.LENGTH_LONG).show()
        }
        holder.imagenDestino.setOnClickListener {
            Snackbar.make(holder.imagenDestino,"La cantidad destino es: ${lista.get(position).getTexto().toString()}",Snackbar.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imagenOrigen: ImageView;
        var imagenDestino: ImageView;

        init {
            imagenOrigen = itemView.findViewById(R.id.imagen_primera)
            imagenDestino = itemView.findViewById(R.id.imagen_segunda)
        }
    }
}