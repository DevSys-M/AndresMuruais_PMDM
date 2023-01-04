package com.example.t4_restaurantes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.t4_restaurantes.adapters.AdapterComida
import com.example.t4_restaurantes.databinding.ActivityMainBinding
import com.example.t4_restaurantes.model.Comida

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var arrayComida: ArrayList<Comida>
    private lateinit var adaptadorComida: AdapterComida

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
            bundle.putString("valoracion",valoracion)
            bundle.putString("comida",tipoComida)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private fun rellenarLista() {
        arrayComida.add(Comida("Todas",R.drawable.restaurant))
        arrayComida.add(Comida("Mediterraneo",R.drawable.medite))
        arrayComida.add(Comida("Italiano",R.drawable.italiano))
        arrayComida.add(Comida("Americana",R.drawable.burger))
        arrayComida.add(Comida("China",R.drawable.chino))
        arrayComida.add(Comida("Vegetariana",R.drawable.vegetariano))
        binding.spinnerPersonalizado.adapter = adaptadorComida
        adaptadorComida.notifyDataSetChanged()
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
            }
            binding.spinnerPersonalizado.id->{
                tipoComida = binding.spinnerPersonalizado.adapter.getItem(p2).toString()
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}