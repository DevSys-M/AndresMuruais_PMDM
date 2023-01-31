package com.example.t6_menu.model

import java.io.Serializable

data class Asignaturas(
    var nombre: String,
    var profesor: String,
    var horas: Int,
    var ciclo: String,
    var curso: Int,
    var imagen: Int
): Serializable