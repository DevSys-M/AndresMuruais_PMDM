package com.example.t7_inciofragment_dos.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.t7_inciofragment_dos.R
import com.example.t7_inciofragment_dos.model.Usuario

class AdaptadorUsuarios (var listaUsuarios: ArrayList<Usuario>, var contexto: Context): RecyclerView.Adapter<AdaptadorUsuarios.MyHolder>() {

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imagen: ImageView;
        var nombre: TextView;

        init {
            imagen = itemView.findViewById(R.id.imagen_item_recycler)
            nombre = itemView.findViewById(R.id.nombre_item_recycler)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val vista: View = LayoutInflater.from(contexto).inflate(R.layout.item_recycler,
            parent,false)
        return  MyHolder(vista)
    }

    override fun getItemCount(): Int {
        return listaUsuarios.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        val usuario: Usuario = listaUsuarios[position]
        holder.nombre.text = usuario.nombre;
        if (usuario.sexo.equals("m",true)){
            holder.imagen.setImageResource(R.drawable.hombre)
        } else {
            holder.imagen.setImageResource(R.drawable.mujer)
        }

    }
}