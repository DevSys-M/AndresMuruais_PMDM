package com.example.t4_conversor.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.t4_conversor.model.Moneda
import com.example.t4_conversor.R

class AdapterMoneda(var contexto: Context, var lista: ArrayList<Moneda>) :
    RecyclerView.Adapter<AdapterMoneda.MyHolder>() {

    private var listener: OnRecyclerListener

    init {
        listener = contexto as OnRecyclerListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view: View =
            LayoutInflater.from(contexto).inflate(R.layout.item_recycler, parent, false)
        return MyHolder(view);
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val moneda = lista[position]
        Log.v("moneda",moneda.toString())
        if (moneda.origen =="euros") {
            holder.imagenOrigen.setImageResource(R.drawable.euro)
        } else if (moneda.origen =="dolar") {
            holder.imagenOrigen.setImageResource(R.drawable.dollar)
        } else if (moneda.origen =="libra") {
            holder.imagenOrigen.setImageResource(R.drawable.libra)
        }
        if (moneda.destino == "euros") {
            holder.imagenDestino.setImageResource(R.drawable.euro)
        } else if (moneda.destino == "dolar") {
            holder.imagenDestino.setImageResource(R.drawable.dollar)
        } else if (moneda.destino == "libra") {
            holder.imagenDestino.setImageResource(R.drawable.libra)
        }
        holder.linear.setOnClickListener {
            listener.onRecyclerSelected(moneda)
        }
    }


    override fun getItemCount(): Int {
        return lista.size
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imagenOrigen: ImageView;
        var imagenDestino: ImageView;
        var linear: LinearLayout

        init {
            imagenOrigen = itemView.findViewById(R.id.imagen_primera)
            imagenDestino = itemView.findViewById(R.id.imagen_segunda)
            linear = itemView.findViewById(R.id.item_linear)
        }
    }

    fun addMoneda(moneda: Moneda) {
        this.lista.add(moneda)
        notifyItemInserted(lista.size - 1)
    }

    interface OnRecyclerListener {
        fun onRecyclerSelected(moneda: Moneda)
    }


}