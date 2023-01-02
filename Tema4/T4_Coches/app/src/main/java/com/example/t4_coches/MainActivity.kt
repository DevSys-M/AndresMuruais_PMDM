package com.example.t4_coches

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.t4_coches.databinding.ActivityMainBinding
import com.example.t4_coches.model.Coche

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listaCoches: ArrayList<Coche>

    // spinner filtrar
    private lateinit var precio: String
    private lateinit var marca: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instancias()
        rellenarDatos()
        asociarDatos()
        acciones()
    }

    private fun acciones() {
        TODO("Not yet implemented")
    }

    private fun asociarDatos() {
        TODO("Not yet implemented")
    }

    private fun rellenarDatos() {
        listaCoches = ArrayList();

        listaCoches.add(Coche("Mercedes", "AMG GT", 500, 200000, "Deportivo", R.drawable.amggt))
        listaCoches.add(Coche("Bentley", "Continental", 400, 300000, "Berlina deportivo", R.drawable.continental))
        listaCoches.add(Coche("Jaguar", "FType", 300, 150000, "Deportivo", R.drawable.ftype))
        listaCoches.add(Coche("Ford", "GT40", 500, 300000, "Deportivo clasico", R.drawable.gt40))
        listaCoches.add(Coche("Nissan", "GTR", 300, 200000, "Deportivo", R.drawable.gtr))
        listaCoches.add(Coche("Porche", "Huayara", 600, 400000, "Deportivo", R.drawable.huayra))
        listaCoches.add(Coche("Lexus", "LC", 200, 100000, "Deportivo", R.drawable.lc))
        listaCoches.add(Coche("Ferrari", "Le ferrari", 600, 600000, "Deportivo", R.drawable.leferrari))
        listaCoches.add(Coche("McLaren", "MC600", 500, 450000, "Deportivo", R.drawable.mc600))
        listaCoches.add(Coche("Toyota", "Supra", 300, 150000, "Deportivo", R.drawable.supra))
        listaCoches.add(Coche("Porche", "Taycan", 350, 250000, "Deportivo", R.drawable.taycan))
        //adaptadorCoche = AdaptadorCoche(this, listaCoches)
    }

    private fun instancias() {
        binding.spinnerMarca.adapter = ArrayAdapter.createFromResource(applicationContext, R.array.lista_marcas, android.R.layout.simple_spinner_item)
        (binding.spinnerMarca.adapter as ArrayAdapter<CharSequence>).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerPrecio.adapter = ArrayAdapter.createFromResource(applicationContext, R.array.lista_precios, android.R.layout.simple_spinner_item)
        (binding.spinnerPrecio.adapter as ArrayAdapter<CharSequence>).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

    }
}