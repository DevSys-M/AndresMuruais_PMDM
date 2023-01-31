package com.example.t4_conversor.model

import java.io.Serializable

class Moneda(var origen: String, var destino: String, var texto: String) : Serializable {
    private lateinit var resultado: String

    fun conversor(): String {
        if (origen.equals("euros") and destino.equals("euros")) {
            resultado = texto
        } else if (origen.equals("euros") and destino.equals("dolar")) {
            resultado = (texto.toDouble() * 1.0858908).toString()
        } else if (origen.equals("euros") and destino.equals("libra")) {
            resultado = (texto.toDouble() * 0.87653576).toString()
        } else if (origen.equals("dolar") and destino.equals("euros")) {
            resultado = (texto.toDouble() * 0.92090287).toString()
        } else if (origen.equals("dolar") and destino.equals("dolar")) {
            resultado = texto
        } else if (origen.equals("dolar") and destino.equals("libra")) {
            resultado = (texto.toDouble() * 0.8072043).toString()
        } else if (origen.equals("libra") and destino.equals("euros")) {
            resultado = (texto.toDouble() * 1.1408548).toString()
        } else if (origen.equals("libra") and destino.equals("dolar")) {
            resultado = (texto.toDouble() * 1.2388437).toString()
        } else if (origen.equals("libra") and destino.equals("libra")) {
            resultado = texto
        }
        return resultado
    }

    override fun toString(): String {
        return "origen: ${origen}" +
                "destino: ${destino}" +
                "texto: ${texto}"

    }

}