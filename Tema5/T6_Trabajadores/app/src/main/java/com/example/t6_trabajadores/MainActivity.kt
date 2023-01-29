package com.example.t6_trabajadores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t6_trabajadores.adapters.AdapterTrabajador
import com.example.t6_trabajadores.databinding.ActivityMainBinding
import com.example.t6_trabajadores.dialogs.DialogoComunica
import com.example.t6_trabajadores.model.Trabajador

class MainActivity : AppCompatActivity(),AdapterTrabajador.OnRecyclerListener {
    private lateinit var binding: ActivityMainBinding

    private lateinit var adaptadorTrabajadores: AdapterTrabajador
    private lateinit var trabajador: Trabajador


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instancias()
        acciones()
    }

    private fun instancias() {
        adaptadorTrabajadores = AdapterTrabajador(this, ArrayList<Trabajador>())
        binding.recycler.adapter= adaptadorTrabajadores
        binding.recycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.spinnerPuesto.adapter =   ArrayAdapter.createFromResource(this,R.array.puestos,android.R.layout.simple_spinner_item)
        (binding.spinnerPuesto.adapter as ArrayAdapter<CharSequence>).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }


    private fun acciones() {
       binding.botonAdd.setOnClickListener{
           trabajador = Trabajador(
               binding.editNombre.text.toString(),
               binding.editApellido.text.toString(),
               binding.editCorreo.text.toString(),
               binding.editEdad.text.toString(),
               binding.spinnerPuesto.selectedItem.toString()
           )
           adaptadorTrabajadores.addTrabajador(trabajador)
       }
    }

    override fun onRecyclerSelected(trabajador: Trabajador) {
        DialogoComunica.newInstance(trabajador).show(supportFragmentManager,null)
        binding.editNombre.setText("")
        binding.editApellido.setText("")
        binding.editCorreo.setText("")
        binding.editEdad.setText("")
        binding.spinnerPuesto.setSelection(0)
    }
}