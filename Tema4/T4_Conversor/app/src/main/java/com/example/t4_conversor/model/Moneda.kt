package com.example.t4_conversor.model

class Moneda(
    private var origen: String,
    private var destino: String,
    private var texto: Int = 0
) {

    private var resultado: Double = 0.0

    fun getOrigen() {
        this.origen
    }

    fun getDestino() {
        this.destino
    }

    fun getTexto() {
        this.texto
    }

    fun calularCambio(): Double {
        if (origen == "euros" && destino == "dolar") {
            resultado = texto * 1.0831391

        } else if (origen == "euros" && destino == "libra") {
            resultado = texto * 0.88657024

        } else if (origen == "euros" && destino == "euros") {
            resultado = texto.toDouble()

        } else if (origen == "dolar" && destino == "dolar") {
            resultado = texto.toDouble()

        } else if (origen == "dolar" && destino == "euros") {
            resultado = texto * 0.92457468

        } else if (origen == "dolar" && destino == "libras") {
            resultado = texto * 0.81963498

        } else if (origen == "libras" && destino == "libras") {
            resultado = texto.toDouble()

        } else if (origen == "libras" && destino == "euros") {
            resultado = texto * 1.127969

        } else if (origen == "libras" && destino == "dolar") {
            resultado = texto * 1.2197641

        }
        return resultado
    }
}