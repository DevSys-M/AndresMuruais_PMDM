package com.example.t3_hobbies.model

import java.io.Serializable

class Hobbies(private var nombre: String,private var detalle: String,private var imagen: Int,private var categoria: String): Serializable {

    fun getNombre(): String{
        return this.nombre
    }
    fun getDetalle(): String{
        return this.detalle
    }
    fun getImagen(): Int{
        return this.imagen
    }
    fun getCategoria(): String{
        return this.categoria
    }
    

}