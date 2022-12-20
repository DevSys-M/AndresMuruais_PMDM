package trabajadoresHerencia.model

open class Autonomo(nombre: String, apellido: String, dni: String):  Trabajadores(nombre,apellido,dni) {




    override fun isContratado() {
        contratado=true;
    }

}