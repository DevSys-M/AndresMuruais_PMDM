package com.example.t8_sqlite.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.t8_sqlite.dao.AlumnoDao
import com.example.t8_sqlite.model.Alumno

@Database(entities = [Alumno::class],version=1, exportSchema = false)
abstract class DatabaseAlumnos: RoomDatabase() {
    abstract fun alumnoDAO(): AlumnoDao
    companion object DatabaseBuilder{
        private var INSTANCE : DatabaseAlumnos ? = null
        fun getInstance (context: Context): DatabaseAlumnos {
            if (INSTANCE == null) synchronized(Alumno::class) {
                INSTANCE = buildRoomDB(context)
            }
            return INSTANCE!!
        }

        private fun buildRoomDB (contexto : Context) =
            Room.databaseBuilder (
                contexto.applicationContext, DatabaseAlumnos::class.java, "alumnos.db"
            ).build ()
    }
}