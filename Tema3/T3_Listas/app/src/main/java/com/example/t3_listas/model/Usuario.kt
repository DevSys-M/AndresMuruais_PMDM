package com.example.t3_listas.model

class Usuario(var nombre: String,var telefono: Int, var genero: String) {

    override fun toString(): String {
        return nombre
    }
}