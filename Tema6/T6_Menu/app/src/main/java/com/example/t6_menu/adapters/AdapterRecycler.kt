package com.example.t6_menu.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.t6_menu.R

class AdapterRecycler(var context: Context, var lista: ArrayList<String>):
    RecyclerView.Adapter<AdapterRecycler.MyHolder>() {

        inner class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            var textoNombre: TextView

            init {
                textoNombre = itemView.findViewById(R.id.texto_item)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val vista = LayoutInflater.from(context).inflate(R.layout.item_recycler,parent,false)
    }




    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        lista.size

}