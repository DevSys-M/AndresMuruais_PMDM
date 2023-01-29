package com.example.t5_reservas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.t5_reservas.R
import com.example.t5_reservas.model.Reservas

class AdapterRecycler(var context: Context, var lista: ArrayList<Reservas>):
    RecyclerView.Adapter<AdapterRecycler.MyHolder>() {
    inner class MyHolder(itemView : View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false)
        return MyHolder(view);
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        lista.size
    }
}