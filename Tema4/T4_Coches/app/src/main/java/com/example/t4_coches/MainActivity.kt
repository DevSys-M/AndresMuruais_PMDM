package com.example.t4_coches

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t4_coches.adapters.AdaptadorCoche
import com.example.t4_coches.databinding.ActivityMainBinding
import com.example.t4_coches.model.Coche

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listaCoches: ArrayList<Coche>
    private lateinit var adaptadorCoche: AdaptadorCoche

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
        adaptadorCoche.onCocheOnClick={coche: Coche ->
            var intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("coche",coche)
            startActivity(intent)
        }
        binding.spinnerPrecio.onItemSelectedListener= this
        binding.spinnerMarca.onItemSelectedListener=this

    }

    private fun asociarDatos() {
        binding.recycler.adapter = adaptadorCoche
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
        adaptadorCoche = AdaptadorCoche(this, listaCoches)
    }

    private fun instancias() {
        binding.spinnerMarca.adapter = ArrayAdapter.createFromResource(applicationContext, R.array.lista_marcas, android.R.layout.simple_spinner_item)
        (binding.spinnerMarca.adapter as ArrayAdapter<CharSequence>).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerPrecio.adapter = ArrayAdapter.createFromResource(applicationContext, R.array.lista_precios, android.R.layout.simple_spinner_item)
        (binding.spinnerPrecio.adapter as ArrayAdapter<CharSequence>).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.recycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when (p0!!.id) {
            binding.spinnerPrecio.id -> {
                precio = binding.spinnerPrecio.adapter.getItem(p2).toString()
                when (p2) {
                    0 -> {
                        adaptadorCoche.cambiarLista(listaCoches)
                    }
                    1 -> {
                        // hasta 100000
                        // de la lista de coche que esta en el spinner saco los que valen hasta 100mil
                        adaptadorCoche.cambiarLista(adaptadorCoche.lista.filter { it.precio <= 100000 } as ArrayList<Coche>)
                    }
                    2 -> {
                        adaptadorCoche.cambiarLista(adaptadorCoche.lista.filter { it.precio <= 200000 } as ArrayList<Coche>)

                    }
                    3 -> {
                        adaptadorCoche.cambiarLista(adaptadorCoche.lista.filter { it.precio <= 300000 } as ArrayList<Coche>)

                    }
                    4 -> {
                        adaptadorCoche.cambiarLista(adaptadorCoche.lista.filter { it.precio <= 400000 } as ArrayList<Coche>)

                    }
                    5 -> {
                        adaptadorCoche.cambiarLista(adaptadorCoche.lista.filter { it.precio >= 500000 } as ArrayList<Coche>)

                    }
                }
            }
            binding.spinnerMarca.id -> {
                marca = binding.spinnerMarca.adapter.getItem(p2).toString()
                // método filter da una lista ya filtrada sobre una condicion
                if (marca.equals("todos", true)) {
                    adaptadorCoche.cambiarLista(listaCoches)
                } else {
                    adaptadorCoche.cambiarLista(listaCoches.filter { it.marca == marca } as ArrayList)
                }
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}