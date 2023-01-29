package com.example.t4_restaurantes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t4_restaurantes.adapters.AdapterComida
import com.example.t4_restaurantes.adapters.AdapterRestaurante
import com.example.t4_restaurantes.databinding.ActivityMainBinding
import com.example.t4_restaurantes.model.Comida
import com.example.t4_restaurantes.model.Restaurante

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var arrayComida: ArrayList<Comida>
    private lateinit var adaptadorComida: AdapterComida

    private lateinit var listaRestaurantesPasar: ArrayList<Restaurante>
    private lateinit var listaRestaurantes: ArrayList<Restaurante>

    private lateinit var valoracion: String
    private lateinit var tipoComida: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instancias()
        rellenarLista()
        acciones()
    }

    private fun acciones() {
        binding.spinnerValoracion.onItemSelectedListener= this
        binding.spinnerPersonalizado.onItemSelectedListener= this

        binding.botonRestaurante.setOnClickListener {
            var intent= Intent(this,RestauranteActivity::class.java)
            var bundle: Bundle = Bundle()
            bundle.putSerializable("restaurante",listaRestaurantesPasar)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private fun rellenarLista() {
        arrayComida.add(Comida("Todos",R.drawable.restaurant))
        arrayComida.add(Comida("Mediterranea",R.drawable.medite))
        arrayComida.add(Comida("Italiano",R.drawable.italiano))
        arrayComida.add(Comida("Americano",R.drawable.burger))
        arrayComida.add(Comida("Chino",R.drawable.chino))
        arrayComida.add(Comida("Vegetariano",R.drawable.vegetariano))
        binding.spinnerPersonalizado.adapter = adaptadorComida
        adaptadorComida.notifyDataSetChanged()


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
        listaRestaurantes.add(Restaurante("Vegetariano 1", 10, "Vegetariano", 8123,R.drawable.vegetariana))
        listaRestaurantes.add(Restaurante("Vegetariano 2", 5, "Vegetariano", 7123,R.drawable.vegetariana))
        listaRestaurantes.add(Restaurante("Vegetariano 3", 6, "Vegetariano", 6234,R.drawable.vegetariana))
        listaRestaurantes.add(Restaurante("Americano 1", 6, "Americano", 6234,R.drawable.americana))
        listaRestaurantes.add(Restaurante("Americano 2", 9, "Americano", 6234,R.drawable.americana))
        listaRestaurantes.add(Restaurante("Americano 3", 6, "Americano", 6234,R.drawable.americana))
        listaRestaurantes.add(Restaurante("Americano 4", 7, "Americano", 6234,R.drawable.americana))

    }

    private fun instancias() {
        arrayComida = ArrayList()
        binding.spinnerValoracion.adapter = ArrayAdapter.createFromResource(applicationContext,R.array.lista_valoracion,android.R.layout.simple_spinner_item)
        (binding.spinnerValoracion.adapter as ArrayAdapter<CharSequence>).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adaptadorComida = AdapterComida(arrayComida,applicationContext)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when(p0!!.id){
            binding.spinnerValoracion.id->{
                valoracion = binding.spinnerValoracion.adapter.getItem(p2).toString()

                when(p2){
                    0 ->{
                        listaRestaurantesPasar= listaRestaurantes
                    }
                    1->{
                        listaRestaurantesPasar = listaRestaurantes.filter { it.valoracion <= 1 } as ArrayList<Restaurante>
                    }
                    2->{
                        listaRestaurantesPasar = listaRestaurantes.filter { it.valoracion <= 2 } as ArrayList<Restaurante>
                    }
                    3->{
                        listaRestaurantesPasar = listaRestaurantes.filter { it.valoracion <= 3 } as ArrayList<Restaurante>
                    }
                    4->{
                        listaRestaurantesPasar = listaRestaurantes.filter { it.valoracion <= 4 } as ArrayList<Restaurante>
                    }
                    5->{
                        listaRestaurantesPasar = listaRestaurantes.filter { it.valoracion <= 5 } as ArrayList<Restaurante>
                    }
                    6->{
                        listaRestaurantesPasar = listaRestaurantes.filter { it.valoracion <= 6 } as ArrayList<Restaurante>
                    }
                    7->{
                        listaRestaurantesPasar = listaRestaurantes.filter { it.valoracion <= 7 } as ArrayList<Restaurante>
                    }
                    8->{
                        listaRestaurantesPasar = listaRestaurantes.filter { it.valoracion <= 8 } as ArrayList<Restaurante>
                    }
                    9->{
                        listaRestaurantesPasar = listaRestaurantes.filter { it.valoracion <= 9 } as ArrayList<Restaurante>
                    }
                    10->{
                        listaRestaurantesPasar = listaRestaurantes.filter { it.valoracion <= 10 } as ArrayList<Restaurante>
                    }
                }
            }
            binding.spinnerPersonalizado.id->{
                tipoComida = binding.spinnerPersonalizado.adapter.getItem(p2).toString()
                Log.v("tipocomida",tipoComida)
                if(tipoComida.equals("todos",true)){
                    listaRestaurantesPasar= listaRestaurantes
                }
                else{
                    listaRestaurantesPasar = listaRestaurantes.filter { it.tipoComida == tipoComida } as ArrayList<Restaurante> }
            }
        }
    }
    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}