package trabajadoresHerencia.model

open abstract class Trabajadores (nombre: String, apellido: String, dni: String) : Persona(nombre, apellido, dni){

    var sueldo: Double = 0.0;
    var contratado: Boolean=true;

    constructor(nombre: String, apellido: String, dni: String,sueldo: Double) : this(nombre, apellido, dni){
        this.sueldo=sueldo;
    }
    abstract fun isContratado()

    override fun mostrarDatos(){
        super.mostrarDatos();
        println("SueldoAnual: $sueldo");
        if (contratado) {
            println("Contratado")
        }else{
            println("No contratado")
        }

    }
}