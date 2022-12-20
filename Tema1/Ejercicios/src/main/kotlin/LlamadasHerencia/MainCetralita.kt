import LlamadasHerencia.model.Centralita
import LlamadasHerencia.model.LlamadaLocal
import LlamadasHerencia.model.LlamadaNacional
import LlamadasHerencia.model.LlamadaProvincial

fun main() {
    var centralita: Centralita = Centralita();
    centralita.registrarLlamada(LlamadaLocal(1,2,"Madrid"))
    centralita.registrarLlamada(LlamadaNacional(1,2,3,1))
    centralita.registrarLlamada(LlamadaProvincial(1,2,3))

}