package com.example.t4_holderfuncion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t4_holderfuncion.adapter.AdaptadorLenguajes
import com.example.t4_holderfuncion.databinding.ActivityMainBinding
import com.example.t4_holderfuncion.model.Lenguaje

/*
Por último en el MainActivity no sería necesario implementar nada
ya que no hemos creado una interfaz sino que hemos realizado una función, la cual es llamada desde otra función.
 */
class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private lateinit var arrayLenguajes: ArrayList<Lenguaje>
    private lateinit var adaptadorLenguaje: AdaptadorLenguajes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rellenarDatos()
        configurarRecycler()
        acciones()
    }
    //Como se puede ver en el método acciones
    //la función creada en el adaptador es llamada, teniendo como parámetros el lenguaje que ha sido seleccionado en el listado.
    private fun acciones() {
        adaptadorLenguaje.onLenguajeClick = { lenguaje ->
            Log.v("prueba", lenguaje.nombre)
        }
    }

    private fun configurarRecycler() {
        binding.recyclerLenguajes.adapter = adaptadorLenguaje
        binding.recyclerLenguajes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        binding.recyclerLenguajes.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.HORIZONTAL
            )
        )
        binding.recyclerLenguajes.itemAnimator = DefaultItemAnimator()
    }

    private fun rellenarDatos() {

        arrayLenguajes = ArrayList();
        arrayLenguajes.add(Lenguaje("Kotlin", 1.7, "JetBrain", R.drawable.kotlin))
        arrayLenguajes.add(Lenguaje("Kotlin", 1.7, "JetBrain", R.drawable.kotlin))
        arrayLenguajes.add(Lenguaje("Kotlin", 1.7, "JetBrain", R.drawable.kotlin))
        arrayLenguajes.add(Lenguaje("Kotlin", 1.7, "JetBrain", R.drawable.kotlin))
        arrayLenguajes.add(Lenguaje("Kotlin", 1.7, "JetBrain", R.drawable.kotlin))
        arrayLenguajes.add(Lenguaje("Kotlin", 1.7, "JetBrain", R.drawable.kotlin))
        arrayLenguajes.add(Lenguaje("Kotlin", 1.7, "JetBrain", R.drawable.kotlin))
        arrayLenguajes.add(Lenguaje("KotlinÚltimo", 1.7, "JetBrain", R.drawable.kotlin))
        adaptadorLenguaje = AdaptadorLenguajes(this, arrayLenguajes)

    }


}