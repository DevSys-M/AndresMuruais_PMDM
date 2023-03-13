package com.example.t8_proyectologin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.t8_proyectologin.R
import com.example.t8_proyectologin.model.Producto
import com.google.android.material.snackbar.Snackbar

class AdapterRecycler(var context: Context): RecyclerView.Adapter<AdapterRecycler.MyHolder>() {

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
        val producto= listaProducto[position]
        holder.textoNombre.text = producto.nombre
        holder.textoValor.text = producto.valor.toString()
        holder.layoutCompleto.setOnClickListener {
            //pulsacion
            Snackbar.make(holder.layoutCompleto,(producto?.cv ?:"no hay CV") as String,Snackbar.LENGTH_SHORT).show()
        }
    }fun vaciarLista(){
        listaProducto.clear()
        notifyDataSetChanged()
    }
    fun agregarProducto(producto: Producto){
        this.listaProducto.add(producto)
        notifyItemInserted(listaProducto.size-1)
    }




    //1 Holder
    class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var textoNombre: TextView = itemView.findViewById(R.id.nombre_recycler)
        var textoValor: TextView = itemView.findViewById(R.id.valor_recycler)
        var layoutCompleto: LinearLayout = itemView.findViewById(R.id.layout)
    }


}