package com.example.t3_cartas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.t3_cartas.databinding.ActivitySecondBinding
import com.google.android.material.snackbar.Snackbar

class SecondActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var nombreRecuperado: String
    var puntos: Int = 0
    var cartaAleatorio: Int =0
    var cartaPrimera: Int= (0..12).random()
    var cartaSegunda: Int = (0..12).random()
    private var cartas: IntArray = intArrayOf(
        R.drawable.c1,
        R.drawable.c2,
        R.drawable.c3,
        R.drawable.c4,
        R.drawable.c4,
        R.drawable.c5,
        R.drawable.c6,
        R.drawable.c7,
        R.drawable.c8,
        R.drawable.c9,
        R.drawable.c10,
        R.drawable.c11,
        R.drawable.c12,
        R.drawable.c13
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recuperarDatos()
        iniciarGUI()
    }

    private fun recuperarDatos() {
        val bundle: Bundle? = intent.extras
        nombreRecuperado = bundle?.getString("nombre", "defecto") ?: "defecto null bundle"
    }

    private fun iniciarGUI() {
        val snackbar = Snackbar.make(binding.textNombreRecuperado,
            "Bienvenido: " + nombreRecuperado,
            Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Empezar", { acciones() }).show()
    }

    private fun acciones() {
        binding.imagenCarta.setImageResource(cartas[cartaPrimera])
        binding.botonArriba.setOnClickListener(this)
        binding.botonAbajo.setOnClickListener(this)

    }
    private fun aleatorio(): Int{
        cartaAleatorio = (0..12).random()
        return cartaAleatorio
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.boton_arriba -> {
                if (cartaPrimera <= cartaSegunda) {
                    binding.imagenCarta.setImageResource(cartas[cartaSegunda])
                    puntos++
                    cartaPrimera = cartaSegunda
                    cartaSegunda = aleatorio()
                } else {
                    binding.botonAbajo.isEnabled = !binding.botonAbajo.isEnabled
                    binding.botonArriba.isEnabled = !binding.botonArriba.isEnabled
                    binding.imagenCarta.setImageResource(cartas[cartaSegunda])
                    var snackbar = Snackbar.make(binding.botonAbajo,
                        "Puntos: " + puntos.toString(),
                        Snackbar.LENGTH_INDEFINITE)
                    snackbar.setAction("Volver", {
                        var intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                    }).show()
                }
            }
            R.id.boton_abajo -> {
                if (cartaPrimera >= cartaSegunda) {
                    binding.imagenCarta.setImageResource(cartas[cartaSegunda])
                    puntos++
                    cartaPrimera = cartaSegunda
                    cartaSegunda = aleatorio()
                } else {
                    binding.botonAbajo.isEnabled = !binding.botonAbajo.isEnabled
                    binding.botonArriba.isEnabled = !binding.botonArriba.isEnabled
                    binding.imagenCarta.setImageResource(cartas[cartaSegunda])
                    var snackbar = Snackbar.make(binding.botonAbajo,
                        "Puntos: " + puntos.toString(),
                        Snackbar.LENGTH_INDEFINITE)
                    snackbar.setAction("Volver", {
                        var intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                    }).show()
                }
            }
        }
    }
}