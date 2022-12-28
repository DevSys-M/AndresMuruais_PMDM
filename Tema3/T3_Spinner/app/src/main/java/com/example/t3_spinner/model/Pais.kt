package com.example.t3_spinner.model

import java.io.Serializable

class Pais(private var nombre: String,private var imagen: Int,private var numeroTitulos: Int, private var jugadorEstrella: String):
    Serializable {

    fun getNombre(): String{
        return this.nombre;
    }
    fun getTitulos(): Int{
        return this.numeroTitulos;
    }
    fun getImagen(): Int{
        return this.imagen;
    }
    fun getJugadorEstrella(): String{
        return this.jugadorEstrella;
    }

    override fun toString(): String {
        return getNombre()
    }

}