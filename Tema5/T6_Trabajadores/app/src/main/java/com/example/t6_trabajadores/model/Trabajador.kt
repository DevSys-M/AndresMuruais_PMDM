package com.example.t6_trabajadores.model


import java.io.Serializable

data class Trabajador(
    var nombre: String,
    var apellidos: String,
    var correo: String,
    var edad: String,
    var puesto: String
) : Serializable