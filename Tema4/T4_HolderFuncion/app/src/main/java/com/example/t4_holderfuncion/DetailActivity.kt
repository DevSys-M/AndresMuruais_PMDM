package com.example.t4_holderfuncion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.t4_holderfuncion.databinding.ActivityDetailBinding
import com.example.t4_holderfuncion.model.Lenguaje

class DetailActivity : AppCompatActivity() {

    private lateinit var lenguajeRecuperado: Lenguaje;
    private lateinit var  binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        recuperarDatos()
    }

    private fun recuperarDatos() {
        lenguajeRecuperado = intent.extras?.getSerializable("lenguaje") as Lenguaje;
        binding.imagenDetalle.setImageResource(lenguajeRecuperado.imagen)
        binding.nombreDetalle.setText(lenguajeRecuperado.nombre)
        binding.versionDetalle.setText(lenguajeRecuperado.version.toString())
        binding.textDetalle.setText(lenguajeRecuperado.detalle)

    }
}