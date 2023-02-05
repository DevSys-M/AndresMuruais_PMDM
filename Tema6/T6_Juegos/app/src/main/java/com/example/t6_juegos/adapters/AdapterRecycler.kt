package com.example.t6_juegos.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.t6_juegos.R
import com.example.t6_juegos.model.Juegos

class AdapterRecycler(var lista: ArrayList<Juegos>, var context: Context): RecyclerView.Adapter<AdapterRecycler.MyHolder>() {

    var onJuegosOnClick: ((juego: Juegos)->Unit)? = null

    inner class MyHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var textoNombre: TextView
        var imagenJuego: ImageView
        var toolbarDetalle: Toolbar

        init {
            textoNombre = itemView.findViewById(R.id.texto_item)
            imagenJuego = itemView.findViewById(R.id.imagen_item)
            toolbarDetalle = itemView.findViewById(R.id.toolbar_item)
        }
    }

    fun addJuego(juegos: Juegos){
        this.lista.add(juegos)
        notifyItemInserted(lista.size-1)
    }

    fun filtarJuego(string: ArrayList<String>){
        /*
        this.lista.filter { it.plataforma == string }
        notifyDataSetChanged()

         */
    }

    fun verFavoritos(){
        //revisar favoritos
        this.lista.filter { it.favorito }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val vista = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false)
        return MyHolder(vista)
    }

    override fun getItemCount(): Int {
        return  lista.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val juego= lista[position]
        holder.imagenJuego.setImageResource(juego.imagen)
        holder.textoNombre.text= juego.plataforma
        holder.toolbarDetalle.title = juego.nombre
        holder.toolbarDetalle.inflateMenu(R.menu.menu_detalle)
        holder.toolbarDetalle.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_item_favorito->{
                    juego.favorito = true
                }
                R.id.menu_item_detalle->{
                    //pasar activity
                    onJuegosOnClick?.invoke(juego)
                }
            }
            return@setOnMenuItemClickListener true
        }
    }


}