package com.example.t8_simulacro.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.t8_simulacro.R
import com.example.t8_simulacro.models.Ligas

class AdaptadorLigas(var context: Context): RecyclerView.Adapter<AdaptadorLigas.MyHolder>() {

   private var listaLigas : ArrayList<Ligas>


   init {
       listaLigas= ArrayList()
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val vista: View = LayoutInflater.from(context).inflate(R.layout.item_ligas,parent,false)
        return MyHolder(vista)
    }

    override fun getItemCount(): Int {
        return listaLigas.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val ligas = listaLigas[position]
        holder.textoLiga.text = ligas.nombre.toString()
        holder.favoritas = ligas.favoritas
    }

    inner class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var textoLiga: TextView = itemView.findViewById(R.id.item_liga)
        var favoritas: CheckBox = itemView.findViewById(R.id.item_checkbox)
    }

    fun agregarLiga(ligas: Ligas){
        this.listaLigas.add(ligas)
        notifyItemInserted(listaLigas.size-1)
    }


}