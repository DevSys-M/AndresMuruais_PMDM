package LlamadasHerencia.model

class LlamadaLocal(numeroOrigen: Int, numeroDestino: Int, duracion: Int):
    Llamada(numeroOrigen, numeroDestino, duracion) {

    private var localidad: String? =null;

    constructor(numeroOrigen: Int,duracion: Int,localidad: String) : this(numeroOrigen,numeroOrigen,duracion){
        this.localidad=localidad;
    }

    override fun mostrarDatos() {
        println("Llamada Local")
        super.mostrarDatos()
        println("Localidad: $localidad")

    }

}