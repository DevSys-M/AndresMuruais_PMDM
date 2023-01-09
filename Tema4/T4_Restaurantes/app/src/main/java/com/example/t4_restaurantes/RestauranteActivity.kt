package com.example.t4_restaurantes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t4_restaurantes.adapters.AdapterRestaurante
import com.example.t4_restaurantes.databinding.ActivityRestauranteBinding
import com.example.t4_restaurantes.model.Restaurante


class RestauranteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRestauranteBinding

    private lateinit var listaRestaurantesRecuperado: ArrayList<Restaurante>
    private lateinit var adaptadorRestaurante: AdapterRestaurante


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestauranteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instancias()
        recuperarDatos()
        asociarDatos()
    }

    private fun asociarDatos() {
       binding.recycler.adapter= adaptadorRestaurante
    }

    private fun recuperarDatos() {
        listaRestaurantesRecuperado = intent.extras?.getSerializable("restaurante") as ArrayList<Restaurante>
        adaptadorRestaurante = AdapterRestaurante(this,listaRestaurantesRecuperado)
    }

    private fun instancias() {
        binding.recycler.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

    }
}