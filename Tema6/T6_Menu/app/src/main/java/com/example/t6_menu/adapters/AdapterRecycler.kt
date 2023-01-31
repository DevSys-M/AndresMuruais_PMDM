package com.example.t6_menu.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.t6_menu.R
import com.example.t6_menu.model.Asignaturas
import com.google.android.material.snackbar.Snackbar

class AdapterRecycler(var context: Context, var lista: ArrayList<Asignaturas>) :
    RecyclerView.Adapter<AdapterRecycler.MyHolder>() {

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textoNombre: TextView
        var imagenAsignaturas: ImageView
        var toolbarDetalle: Toolbar


        init {
            textoNombre = itemView.findViewById(R.id.texto_item)
            imagenAsignaturas = itemView.findViewById(R.id.imagen_item)
            toolbarDetalle = itemView.findViewById(R.id.toolbar_item)
        }
    }

    fun agregarDato(asignatura: Asignaturas) {
        lista.add(asignatura)
        notifyItemInserted(lista.size - 1)
    }

    fun vaciarLista() {
        lista.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val vista = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false)
        return MyHolder(vista)
    }

    // para las asignaturas de primero --> menu (ver temario / ver profesor)
    // y color diferente
    // para las asignaturas de segundo --> menu (ver detalles / ver horas)


    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val asignatura = lista[position]

        holder.imagenAsignaturas.setImageResource(asignatura.imagen)
        holder.toolbarDetalle.setTitle(asignatura.nombre)
        if (asignatura.curso == 1) {
            holder.toolbarDetalle.inflateMenu(R.menu.menu_item_uno)
            holder.toolbarDetalle.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_700))
            holder.toolbarDetalle.setOnMenuItemClickListener {

                when (it.itemId) {
                    R.id.menu_item_temario -> {
                        Snackbar.make(
                            holder.imagenAsignaturas,
                            "${asignatura.nombre}",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    R.id.menu_item_profesor -> {
                        Snackbar.make(
                            holder.imagenAsignaturas,
                            "${asignatura.profesor}",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }

                return@setOnMenuItemClickListener true
            }
        }
        else {
            holder.toolbarDetalle.inflateMenu(R.menu.menu_item_dos)
            holder.toolbarDetalle.setOnMenuItemClickListener {

                when (it.itemId) {
                    R.id.menu_item_detalle -> {
                        Snackbar.make(
                            holder.imagenAsignaturas,
                            "${asignatura.nombre}",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    R.id.menu_item_horas -> {
                        Snackbar.make(
                            holder.imagenAsignaturas,
                            "${asignatura.horas}",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }

                return@setOnMenuItemClickListener true
            }

        }
        holder.textoNombre.text = asignatura.nombre
        holder.textoNombre.setOnLongClickListener {
            lista.remove(asignatura)
            this.notifyItemRemoved(position)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }

}