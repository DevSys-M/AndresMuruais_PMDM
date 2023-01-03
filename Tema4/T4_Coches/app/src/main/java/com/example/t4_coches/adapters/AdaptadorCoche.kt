package com.example.t4_coches.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.t4_coches.R
import com.example.t4_coches.model.Coche

class AdaptadorCoche(var context: Context, var lista: ArrayList<Coche>): RecyclerView.Adapter<AdaptadorCoche.MyHolder>() {

    //1a. Creo un objeto de la interfaz para poder utilizarlo

    /*
    Otra forma de hacerlo también es declarando una función en vez de una interfaz en el adaptador, de forma que estará disponible desde el objeto adaptador de la clase MainActivity
     */
    //1b. Funciones vacías: en el origen de los datos crear una variable de tipo funcion

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val coche= lista.get(position)
        holder.modelo.text= coche.modelo
        holder.imagen.setImageResource(coche.imagen)
        holder.boton.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return lista.size

    }

    //Clase anidada MyHolder item:recycler.xml
    inner class MyHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var imagen: ImageView
        var modelo: TextView
        var boton: Button

        init {
            imagen = itemView.findViewById(R.id.imagen_holder)
            modelo = itemView.findViewById(R.id.texto_holder)
            boton = itemView.findViewById(R.id.boton_detalle_holder)
        }
    }


}