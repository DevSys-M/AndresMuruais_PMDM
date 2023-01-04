package com.example.t4_restaurantes.model

import java.io.Serializable

class Comida(private var nombre: String,private var imagen: Int): Serializable {

    fun getNombre(): String{
         return this.nombre
    }
    fun getImagen(): Int{
       return this.imagen
    }

    override fun toString(): String {
        return getNombre()
    }
}