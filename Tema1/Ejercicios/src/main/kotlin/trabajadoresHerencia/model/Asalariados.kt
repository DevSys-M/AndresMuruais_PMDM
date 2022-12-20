package trabajadoresHerencia.model

open class Asalariados(nombre: String, apellido: String, dni: String): Trabajadores(nombre,apellido,dni) {

    var numeroDePagas: Int = 12;
    var sueldoMensual: Double = 0.0

    init {
        sueldoMensual = sueldo/numeroDePagas
    }

    override fun isContratado() {
        contratado=true;
    }

    override fun mostrarDatos() {
        super.mostrarDatos()
        println("SueldoMensual $sueldoMensual")
        println("Numero de Pagas $numeroDePagas")
    }
}