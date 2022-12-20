package model

open class Persona (var nombre: String, var apellido: String, var edad: Int) {
    //variables nombre, apellido, edad
    //todas las varibles de clase tienen getter setter implicitos

    var peso: Double? = null;
    lateinit var dni: String;

    constructor(nombre: String,apellido: String,edad: Int,peso: Double): this(nombre,apellido,edad){
        this.peso =peso;
    }

   open fun mostrarDatos(){
        println("Nombre: $nombre")
        println("Apellidos: $apellido")
        println("Edad: $edad")
    }

    fun setDNI(dni: String){
        this.dni = dni
    }

    fun getDNI(): String{
        return this.dni;
    }
}