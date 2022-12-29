package com.example.t3_listas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.t3_listas.adapter.AdapterUsuario
import com.example.t3_listas.databinding.ActivityMainBinding
import com.example.t3_listas.model.Usuario
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //private lateinit var adapterBase: ArrayAdapter<CharSequence>
    //private lateinit var adapterBase: ArrayAdapter<Usuario>
    private lateinit var listaUsuario: ArrayList<Usuario>
    private lateinit var adaptadorLista: AdapterUsuario


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instancias()
        asociasDatos()
        acciones()
    }

    private fun acciones() {
        binding.listaUsuarios.setOnItemClickListener { adapterView, view, i, l ->

            Snackbar.make(binding.listaUsuarios,adaptadorLista.getItem(i)!!.telefono.toString(), Snackbar.LENGTH_SHORT).show()

            //Snackbar.make(binding.listaUsuarios,adapterBase.getItem(i)!!.telefono.toString(), Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun asociasDatos() {
        // binding.listaUsuarios.adapter = adapterBase;
        binding.listaUsuarios.adapter = adaptadorLista;
    }

    private fun instancias() {
        // adapterBase = ArrayAdapter.createFromResource(applicationContext,R.array.usuarios,android.R.layout.simple_list_item_1)
        listaUsuario = ArrayList()
        listaUsuario.add(Usuario("Borja",123123,"masculino"))
        listaUsuario.add(Usuario("Juan", 456456,"femenino"))
        listaUsuario.add(Usuario("Alba", 321321,"masculino"))
        listaUsuario.add(Usuario("Pedro", 789789,"masculino"))
        listaUsuario.add(Usuario("Alicia", 121212,"femenino"))
        listaUsuario.add(Usuario("Borja", 123123,"masculino"))
        listaUsuario.add(Usuario("Juan", 456456,"femenino"))
        listaUsuario.add(Usuario("Alba", 321321,"masculino"))
        listaUsuario.add(Usuario("Pedro", 789789,"masculino"))
        listaUsuario.add(Usuario("Alicia",121212,"femenino"))
        adaptadorLista = AdapterUsuario(listaUsuario,applicationContext)
        // adapterBase = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,listaUsuario)

    }
}