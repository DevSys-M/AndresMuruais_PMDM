package trabajadoresHerencia.model

open class Jefe (nombre: String, apellido: String, dni: String) : Persona(nombre, apellido, dni){


    var acciones: Int = 0;
    var beneficios: Int =0;
    var contratado=true;

    override fun mostrarDatos() {
        super.mostrarDatos()
        println("Acciones: $acciones");
        println("Beneficios: $beneficios");
    }

    fun isContratado(){
        if(contratado){
            println("Esta contratado")
        }
        else{
            println("Esta despedido")
        }

    }
    fun despedirTrabajador(){
        if(contratado){
           contratado=false;
        }
    }
}