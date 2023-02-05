package com.example.t6_juegos.model

import java.io.Serializable

data class Juegos(
    var nombre: String,
    var plataforma: String,
    var categoria: String,
    var favorito: Boolean,
    var precio: Int,
    var imagen: Int
) : Serializable