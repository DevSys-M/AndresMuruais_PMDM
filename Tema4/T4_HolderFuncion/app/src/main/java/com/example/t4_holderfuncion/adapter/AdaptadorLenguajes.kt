package com.example.t4_holderfuncion.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.t4_holderfuncion.R
import com.example.t4_holderfuncion.model.Lenguaje

//declarando una función en vez de una interfaz en el adaptador, de forma que estará disponible desde el objeto adaptador de la clase MainActivity

class AdaptadorLenguajes(var contexto: Context, var listaDatos: List<*>) :
    RecyclerView.Adapter<AdaptadorLenguajes.MyHolder>() {
    //En el Adaptador se declara una función que se inicia como null y se le da valor en el método init
    var onLenguajeClick: ((lenguaje: Lenguaje)-> Unit)? = null
    /*
    Como se puede ver, en la creación de la variable lenguajeClick se define que es de tipo función, recibiendo por parámetros un objeto de tipo Lenguaje y no devolviendo nada (Unit).
    Más adelante en el método init se configura un listener de click a la imagen y se utiliza el método invoke (ya que se ha declarado como null, recordad el nullsafety) y se pasa como parámetro el lenguaje que está en la posición que indica el adaptador
     */
    //TODO BOTONES
    //creación de la variable función
    var onNombreLongClick: ((posicion: Int)-> Unit)? = null

    inner class MyHolder(var view: View) : RecyclerView.ViewHolder(view) {

        var nombre: TextView
        var imagen: ImageView

        init {
            nombre = view.findViewById(R.id.nombre_item)
            imagen = view.findViewById(R.id.imagen_item)
            imagen.setOnClickListener { onLenguajeClick?.invoke(listaDatos.get(adapterPosition) as Lenguaje) }
            //TODO BOTONES
            nombre.setOnLongClickListener({ view: View ->
                onNombreLongClick?.invoke(adapterPosition)
                return@setOnLongClickListener true
            })
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var view: View =
            LayoutInflater.from(contexto).inflate(R.layout.fila_recycler, parent, false)

        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var lenguaje: Lenguaje = listaDatos.get(position) as Lenguaje
        holder.imagen.setImageResource(lenguaje.imagen)
        holder.nombre.setText(lenguaje.nombre)

    }

    override fun getItemCount(): Int {
        return listaDatos.size
    }


}