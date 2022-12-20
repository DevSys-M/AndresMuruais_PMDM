package trabajadoresHerencia.model

class Empresa {
    var trabajadores: ArrayList<Persona>?;

    init {
        trabajadores = ArrayList();
    }

    fun listarTrabajadores(filtro: String) {

        trabajadores?.forEach { persona ->
            when (filtro) {
                "Jefe" -> {
                    if (persona is Jefe) {
                        persona.mostrarDatos()
                    }
                }
                "Asalariado" -> {
                    if (persona::class.simpleName == filtro){
                        persona.mostrarDatos()
                    }

                }
                "Autonomo" -> {
                    if (persona::class.simpleName == filtro) {
                        persona.mostrarDatos()
                    }
                }
                else -> {

                    persona.mostrarDatos()
                }
            }
            //print(it.mostrarDatos())
        }
    }

    fun agregarTrabajadores(filtro: String) {

        trabajadores?.forEach { persona ->
            when (filtro) {
                "Jefe" -> {
                    if (persona is Jefe) {

                        println("Nombre: ")
                        persona.nombre= readLine()!!.toString()
                        println("Apellidos: ")
                        persona.apellido=readLine()!!.toString()
                        println("Acciones: ")
                        persona.acciones=readLine()!!.toInt()
                        println("Beneficios: ")
                        persona.beneficios=readLine()!!.toInt()

                        trabajadores!!.add(persona)

                    }
                }
                "Asalariado" -> {
                    if (persona is Asalariados){

                        println("Nombre: ")
                        persona.nombre= readLine()!!.toString()
                        println("Apellidos: ")
                        persona.apellido=readLine()!!.toString()
                        println("Acciones: ")
                        persona.sueldo=readLine()!!.toDouble()
                        println("Beneficios: ")
                        persona.numeroDePagas=readLine()!!.toInt()


                        trabajadores!!.add(persona)
                    }
                }
                "Autonomo" -> {
                    if (persona::class.simpleName == filtro){

                        trabajadores!!.add(persona)
                    }
                }
            }
        }
    }
}