package com.example.t6_trabajadores.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.t6_trabajadores.R
import com.example.t6_trabajadores.model.Trabajador

class AdapterTrabajador(var context: Context,var lista:ArrayList<Trabajador>):
    RecyclerView.Adapter<AdapterTrabajador.MyHolder>() {


    private  var listener: OnRecyclerListener

    init {
        listener = context as OnRecyclerListener
    }

    inner class MyHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var textoNombre: TextView
        var textoApellido: TextView
        var linear: LinearLayout

        init {
            textoNombre = itemView.findViewById(R.id.item_nombre)
            textoApellido = itemView.findViewById(R.id.item_apellido)
            linear = itemView.findViewById(R.id.linear)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val vista: View = LayoutInflater.from(context).inflate(R.layout.item_recycler,parent,false)
        return MyHolder(vista)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val trabajador = lista[position]
        holder.textoNombre.text = trabajador.nombre
        holder.textoApellido.text = trabajador.apellidos
        holder.linear.setOnClickListener{
            listener.onRecyclerSelected(trabajador)
        }

    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun addTrabajador(trabajador: Trabajador){
        this.lista.add(trabajador)
        notifyItemInserted(lista.size-1)
    }

    interface OnRecyclerListener{
        fun onRecyclerSelected(trabajador: Trabajador)
    }
}