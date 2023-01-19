package com.example.t4_conversor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t4_conversor.adapter.AdapterMoneda
import com.example.t4_conversor.databinding.ActivityMainBinding
import com.example.t4_conversor.dialog.DialogoPersonalizado
import com.example.t4_conversor.model.Moneda

class MainActivity : AppCompatActivity(), DialogoPersonalizado.onDialogoListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listaMoneda: ArrayList<Moneda>
    private lateinit var moneda: Moneda
    private lateinit var adapterMoneda: AdapterMoneda


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instancias()
        asociarDatos()
        acciones()
    }


    private fun instancias() {
        listaMoneda= ArrayList()
        listaMoneda.add(Moneda(moneda.getOrigen().toString(),moneda.getDestino().toString(),moneda.getTexto() as Int))
        adapterMoneda = AdapterMoneda(this,listaMoneda)
    }

    private fun asociarDatos() {
        binding.recycler.adapter = adapterMoneda
        //layout
        binding.recycler.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
    }

    private fun acciones() {
        binding.botonConversor.setOnClickListener {
            DialogoPersonalizado().show(supportFragmentManager,"")
        }
    }

    override fun onDialogoOrigenSelected(mensaje: String) {
        moneda.getOrigen()
    }

    override fun onDialogoDestinoSelected(mensaje: String) {
        moneda.getDestino()
    }

    override fun onDialogoTextoSelected(mensaje: String) {
        moneda.getTexto()
    }

}
