package com.example.t8_examen.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.t8_examen.R
import com.example.t8_examen.model.Producto
import com.google.android.material.snackbar.Snackbar

class AdaptadorProducto(var context: Context): RecyclerView.Adapter<AdaptadorProducto.MyHolder>() {

    private var listaProducto: ArrayList<Producto>

    init {
        listaProducto= ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val vista: View = LayoutInflater.from(context).inflate(R.layout.item_recycler,parent,false)
        return MyHolder(vista)
    }

    override fun getItemCount(): Int {
       return listaProducto.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val producto = listaProducto[position]
        Glide.with(context)
            .load(producto.thumbnail)
            .placeholder(R.drawable.android)
            .into(holder.imagen)
        holder.nombre.text = producto.title
        holder.layout.setOnClickListener {
            Snackbar.make(holder.layout,("El precio es: ${producto.price.toString()}"),Snackbar.LENGTH_SHORT).show()
        }


    }

    fun vaciarLista(){
        listaProducto.clear()
        notifyDataSetChanged()
    }

    fun agregarProducto(producto: Producto){
        this.listaProducto.add(producto)
        notifyItemInserted(listaProducto.size-1)
    }

    inner class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imagen: ImageView = itemView.findViewById(R.id.item_imagen)
        var nombre: TextView = itemView.findViewById(R.id.item_texto)
        var layout: ConstraintLayout = itemView.findViewById(R.id.item_recycler)
    }
}