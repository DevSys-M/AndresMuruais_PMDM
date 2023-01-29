package com.example.t4_conversor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t4_conversor.adapter.AdapterMoneda
import com.example.t4_conversor.databinding.ActivityMainBinding
import com.example.t4_conversor.dialog.DialogoPersonalizado
import com.example.t4_conversor.model.Moneda
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), DialogoPersonalizado.onDialogoListener, AdapterMoneda.OnRecyclerListener{
    private lateinit var binding: ActivityMainBinding
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
        adapterMoneda = AdapterMoneda(this, ArrayList<Moneda>())
    }

    private fun asociarDatos() {
        binding.recycler.adapter = adapterMoneda
        //layout
        binding.recycler.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
    }

    private fun acciones() {
        binding.botonConversor.setOnClickListener {
            DialogoPersonalizado().show(supportFragmentManager, null)
        }
    }

    override fun onDialogoSelected(moneda: Moneda) {
        adapterMoneda.addMoneda(moneda)
        Log.v("asignatura", moneda.toString())
    }

    override fun onRecyclerSelected(moneda: Moneda) {
        Snackbar.make(binding.root,"${moneda.texto} ${moneda.origen} son ${moneda.conversor()} ${moneda.destino}",Snackbar.LENGTH_SHORT).show()
    }


}
