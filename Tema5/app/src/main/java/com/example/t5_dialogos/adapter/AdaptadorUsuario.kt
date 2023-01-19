package com.example.t5_dialogos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.t5_dialogos.R
import com.example.t5_dialogos.model.Usuario

class AdaptadorUsuario(var contexto: Context, var lista: ArrayList<Usuario>) :
    RecyclerView.Adapter<AdaptadorUsuario.MyHolder>() {

    //private lateinit var listener:


        inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var textoNombre: TextView
            var textoDept: TextView
            var linear: LinearLayout




            init {
                textoNombre = itemView.findViewById(R.id.item_nombre)
                textoDept = itemView.findViewById(R.id.item_departamento)
                linear = itemView.findViewById(R.id.item_linear)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
            val vista: View = LayoutInflater.from(contexto)
                .inflate(R.layout.item_recycler, parent, false)
            return MyHolder(vista)
        }

        override fun onBindViewHolder(holder: MyHolder, position: Int) {
            val usuario = lista[position]
            holder.textoNombre.text = usuario.nombre
            holder.textoDept.text = usuario.departamento
            holder.linear.setOnClickListener{

            }
        }

        override fun getItemCount(): Int {
            return lista.size
        }

        fun addUser(usuario: Usuario){
            this.lista.add(usuario)
            notifyItemInserted(lista.size-1)
        }


    }