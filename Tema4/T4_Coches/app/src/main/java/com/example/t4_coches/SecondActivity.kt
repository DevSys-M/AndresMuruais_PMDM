package com.example.t4_coches

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.t4_coches.databinding.ActivitySecondBinding
import com.example.t4_coches.model.Coche

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    lateinit var cocheRecuperado: Coche

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recuperarDatos()
    }

    private fun recuperarDatos() {
        cocheRecuperado = intent.extras?.getSerializable("coche") as Coche
        binding.imagenDetalle.setImageResource(cocheRecuperado.imagen)
        binding.modeloDetalle.setText(cocheRecuperado.modelo)
        binding.cvDetalle.setText(cocheRecuperado.cv.toString())
        binding.tipoDetalle.setText(cocheRecuperado.tipo)
        binding.precioDetalle.setText(cocheRecuperado.precio.toString())
    }
}