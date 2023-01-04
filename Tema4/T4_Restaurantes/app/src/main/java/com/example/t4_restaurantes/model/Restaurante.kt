package com.example.t4_restaurantes.model

import java.io.Serializable

data class Restaurante(var nombre: String,var valoracion: Int,var tipoComida: String,var telefono: Int,var imagen: Int):
    Serializable
