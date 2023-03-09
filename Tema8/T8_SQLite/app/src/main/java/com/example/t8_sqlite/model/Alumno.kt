package com.example.t8_sqlite.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Alumno(
    @ColumnInfo var nombre: String,
    @ColumnInfo var apellido: String,
    @ColumnInfo var edad: Int,
    @ColumnInfo var correo: String): java.io.Serializable {
    @PrimaryKey(true)
    var id: Long =1;

    fun mostrarDatos(){
        println("nombre ${nombre}")
        println("apellido ${apellido}")
        println("correo ${correo}")
        println("edad ${edad}")

    }

}