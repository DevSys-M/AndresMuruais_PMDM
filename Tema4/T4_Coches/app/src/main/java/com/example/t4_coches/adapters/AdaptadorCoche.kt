package com.example.t4_coches.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.t4_coches.R
import com.example.t4_coches.model.Coche

class AdaptadorCoche(var context: Context, var lista: ArrayList<Coche>): RecyclerView.Adapter<AdaptadorCoche.MyHolder>() {

    var onCocheOnClick: ((coche: Coche)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val coche= lista.get(position)
        holder.modelo.text= coche.modelo
        holder.imagen.setImageResource(coche.imagen)
        holder.boton.setOnClickListener {
            onCocheOnClick?.invoke(coche)
        }
    }

    override fun getItemCount(): Int {
        return lista.size

    }
    //creo metodo en el adaptador para actualizar lista
    fun cambiarLista(listaNueva: ArrayList<Coche>){
        this.lista = listaNueva;
        notifyDataSetChanged()
    }

    //Clase anidada MyHolder item:recycler.xml
    inner class MyHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var imagen: ImageView
        var modelo: TextView
        var boton: Button

        init {
            imagen = itemView.findViewById(R.id.imagen_holder)
            modelo = itemView.findViewById(R.id.texto_holder)
            boton = itemView.findViewById(R.id.boton_detalle_holder)

        }
    }


}