package com.example.t6_trabajadores.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.t6_trabajadores.model.Trabajador

class AdapterTrabajador(var context: Context,var lista:ArrayList<Trabajador>):
    RecyclerView.Adapter<AdapterTrabajador.MyHolder>() {

    inner class MyHolder(itemView: View):RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}