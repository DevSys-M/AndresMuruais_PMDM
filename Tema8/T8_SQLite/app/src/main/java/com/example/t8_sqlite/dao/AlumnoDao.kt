package com.example.t8_sqlite.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.t8_sqlite.model.Alumno

@Dao
interface AlumnoDao {

    @Query("SELECT * FROM Alumno")
    fun getAlumnos(): List<Alumno>

    @Query("SELECT * FROM Alumno WHERE nombre = :nombre")
    fun getAlumnosbyName(nombre: String): List<Alumno>

    @Insert
    fun insertarAlumno(alumno: Alumno)

}