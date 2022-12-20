package LlamadasHerencia.model

class Centralita() {

    lateinit var listaLLamadas: ArrayList<Llamada>;
    var costeTotal: Double = 0.0;

    init {
        listaLLamadas = ArrayList();
    }

    fun registrarLlamada(llamada: Llamada) {
        listaLLamadas.add(llamada)
        print("Llamadas con datos:")
        llamada.mostrarDatos()
        costeTotal += llamada.coste;
    }

    fun listarLlamadas() {
        listaLLamadas.forEachIndexed({ index, item ->
            print("Llamada en la posición ${index + 1} ")
            item.mostrarDatos()
        })
    }

    fun mostrarCostesAcumuludados() {
        var costeAcumulado: Double = 0.0
        listaLLamadas.forEach {
            costeAcumulado += it.coste;
        }
        println("El coste acumulado es: $costeAcumulado")
    }

    //que tipo de llamda quiero listar
    // y listo esas llamadas
    fun listarConFiltro(tipo: String) {
        listaLLamadas.forEach {

            if (it::class.simpleName == tipo) {
                it.mostrarDatos()
            }
        }
    }

    fun filtradoNumero() {

        println((listaLLamadas.filter { it::class.simpleName == "LlamadasNacionales" }).size)
        println((listaLLamadas.filter { it::class.simpleName == "LlamadasLocal" }).size)
        println((listaLLamadas.filter { it::class.simpleName == "LlamadasProvincial" }).size)
    }
    fun mostrarCostes() {
        println("El coste acumulado es: $costeTotal")
    }



}