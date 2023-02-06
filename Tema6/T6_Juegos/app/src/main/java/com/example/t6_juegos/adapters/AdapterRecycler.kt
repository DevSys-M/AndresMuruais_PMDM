package com.example.t6_juegos.adapters

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

class AdapterRecycler(var listaRecycler: ArrayList<Juegos>, var context: Context) :
    RecyclerView.Adapter<AdapterRecycler.MyHolder>() {

    var onJuegosOnClick: ((juegos: Juegos) -> Unit)? = null

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textoNombre: TextView
        var imagenJuego: ImageView
        var toolbarDetalle: Toolbar

        init {
            textoNombre = itemView.findViewById(R.id.texto_item)
            imagenJuego = itemView.findViewById(R.id.imagen_item)
            toolbarDetalle = itemView.findViewById(R.id.toolbar_item)
        }
    }

    fun addJuego(juegos: Juegos) {
        this.listaRecycler.add(juegos)
        notifyItemInserted(listaRecycler.size - 1)
    }


    fun filtarJuego(element: String) {
        this.listaRecycler.filter { it.plataforma == element }
        notifyDataSetChanged()


    }

    fun verFavoritos() {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val vista = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false)
        return MyHolder(vista)
    }

    override fun getItemCount(): Int {
        return listaRecycler.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val juegos = listaRecycler[position]
        holder.imagenJuego.setImageResource(juegos.imagen)
        holder.textoNombre.text = juegos.plataforma
        holder.toolbarDetalle.title = juegos.nombre
        holder.toolbarDetalle.inflateMenu(R.menu.menu_detalle)
        holder.toolbarDetalle.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_item_favorito -> {
                    juegos.favorito = true
                }
                R.id.menu_item_detalle -> {
                    //pasar activity
                    onJuegosOnClick?.invoke(juegos)
                }
            }
            return@setOnMenuItemClickListener true
        }
    }

}