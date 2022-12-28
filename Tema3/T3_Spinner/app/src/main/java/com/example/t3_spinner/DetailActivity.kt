package com.example.t3_spinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.t3_spinner.databinding.ActivityDetailBinding
import com.example.t3_spinner.model.Pais

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recuperarDatos()

    }

    private fun recuperarDatos() {
        // intent --> bundle --> dato
        var bundle = intent.extras
        var equipo: Pais =  bundle!!.getSerializable("equipo") as Pais
        binding.textoTitulos.setText(equipo.getTitulos());
        binding.estrellaDetalle.setText(equipo.getJugadorEstrella());
        binding.nombreDetalle.setText(equipo.getNombre())
        binding.imagenDetalle.setImageResource(equipo.getImagen())
    }
}