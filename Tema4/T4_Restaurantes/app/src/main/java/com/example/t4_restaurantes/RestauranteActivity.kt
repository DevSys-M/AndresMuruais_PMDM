package com.example.t4_restaurantes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t4_restaurantes.adapters.AdapterRestaurante
import com.example.t4_restaurantes.databinding.ActivityRestauranteBinding
import com.example.t4_restaurantes.model.Restaurante


class RestauranteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRestauranteBinding
    private lateinit var listaRestaurantes: ArrayList<Restaurante>
    private lateinit var adaptadorRestaurante: AdapterRestaurante

    private lateinit var valoracionRecuperado: String
    private lateinit var tipoComidaRecuperado: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestauranteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instancias()
        rellenarDatos()
        asociarDatos()
    }

    private fun asociarDatos() {
       binding.recycler.adapter= adaptadorRestaurante
    }

    private fun rellenarDatos() {
        listaRestaurantes = ArrayList()

        listaRestaurantes.add(Restaurante("Italiano1", 4, "Italiano", 9111,R.drawable.italiana))
        listaRestaurantes.add(Restaurante("Italiano2", 7, "Italiano", 9222,R.drawable.italiana))
        listaRestaurantes.add(Restaurante("Italiano3", 9, "Italiano", 9333,R.drawable.italiana))
        listaRestaurantes.add(Restaurante("Italiano4", 3, "Italiano", 9444,R.drawable.italiana))
        listaRestaurantes.add(Restaurante("Italiano5", 9, "Italiano", 9555,R.drawable.italiana))
        listaRestaurantes.add(Restaurante("Mediterraneo1", 6, "Mediterranea", 9666,R.drawable.mediterranea))
        listaRestaurantes.add(Restaurante("Mediterraneo2", 7, "Mediterranea", 9777,R.drawable.mediterranea))
        listaRestaurantes.add(Restaurante("Mediterraneo3", 5, "Mediterranea", 9123,R.drawable.mediterranea))
        listaRestaurantes.add(Restaurante("Mediterraneo4", 2, "Mediterranea", 9234,R.drawable.mediterranea))
        listaRestaurantes.add(Restaurante("Chino1", 10, "Chino", 9345,R.drawable.china))
        listaRestaurantes.add(Restaurante("Chino2", 5, "Chino", 9456,R.drawable.china))
        listaRestaurantes.add(Restaurante("Chino3", 6, "Chino", 9567,R.drawable.china))
        listaRestaurantes.add(Restaurante("Vegetariano 1", 10, "Americano", 8123,R.drawable.vegetariana))
        listaRestaurantes.add(Restaurante("Vegetariano 2", 5, "Americano", 7123,R.drawable.vegetariana))
        listaRestaurantes.add(Restaurante("Vegetariano 3", 6, "Americano", 6234,R.drawable.vegetariana))
        listaRestaurantes.add(Restaurante("Americano 1", 6, "Americano", 6234,R.drawable.vegetariana))
        listaRestaurantes.add(Restaurante("Americano 2", 9, "Americano", 6234,R.drawable.americana))
        listaRestaurantes.add(Restaurante("Americano 3", 6, "Americano", 6234,R.drawable.americana))
        listaRestaurantes.add(Restaurante("Americano 4", 7, "Americano", 6234,R.drawable.americana))
        adaptadorRestaurante = AdapterRestaurante(this,listaRestaurantes)
    }

    private fun instancias() {
        binding.recycler.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
    }
}