package trabajadoresHerencia.model

 abstract class Persona (var nombre: String, var apellido: String, var dni: String){


    open fun mostrarDatos(){
        println("Nombre: $nombre")
        println("Apellido: $apellido")
        println("Dni: $dni")
    }

}