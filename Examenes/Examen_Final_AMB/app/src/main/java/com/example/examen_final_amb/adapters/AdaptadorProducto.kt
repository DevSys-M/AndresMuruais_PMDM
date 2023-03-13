package com.example.examen_final_amb.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_final_amb.R
import com.example.examen_final_amb.model.Producto
import com.google.android.material.snackbar.Snackbar

class AdaptadorProducto(var context: Context): RecyclerView.Adapter<AdaptadorProducto.MyHolder>() {

    private var listaProducto: ArrayList<Producto>



    init {
        listaProducto = ArrayList()

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val vista = LayoutInflater.from(context).inflate(R.layout.item_recycler,parent,false)
        return MyHolder(vista)
    }

    override fun getItemCount(): Int {
       return listaProducto.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val producto = listaProducto[position]
        holder.imagen.text = producto.thumbnail
        holder.texto.text = producto.tittle
        holder.constraint.setOnClickListener {
            Snackbar.make(holder.constraint,"El precio es ${producto.price}",Snackbar.LENGTH_SHORT).show()
        }
    }

    fun agregarProducto(producto: Producto){
        listaProducto.add(producto)
        notifyItemInserted(listaProducto.size-1)
    }

    inner  class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            var imagen: TextView
            var texto: TextView
            var constraint: RecyclerView
        init {
            texto = itemView.findViewById(R.id.item_text)
            imagen = itemView.findViewById(R.id.item_imagen)
            constraint = itemView.findViewById(R.id.constraint)
            }
        }


}