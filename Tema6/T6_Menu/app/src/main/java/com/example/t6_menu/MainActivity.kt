package com.example.t6_menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t6_menu.adapters.AdapterRecycler
import com.example.t6_menu.databinding.ActivityMainBinding
import com.example.t6_menu.model.Asignaturas
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adaptadorRecycler: AdapterRecycler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //supportActionBar?.title = "Elemento cambiado"
        //setSupportActionBar(binding.toolbar)
        adaptadorRecycler = AdapterRecycler(this, ArrayList())
        binding.recyclerAsignaturas.adapter = adaptadorRecycler

        binding.recyclerAsignaturas.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        // binding.recyclerAsignaturas
        // adaptador
    }

    //asociar barra superior con el menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //menu donde lo voy a poner
        //menuInflater.inflate(R.menu.menu_main,menu)
        menuInflater.inflate(R.menu.menu_asignaturas, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var texto = ""
        when (item.itemId) {

            R.id.menu_agregar -> {
                adaptadorRecycler.agregarDato(
                    Asignaturas(
                        "JAVA",
                        "Fernando",
                        6,
                        "DAM",
                        1,
                        R.drawable.android
                    )
                )
            }
            R.id.menu_vaciar -> {
                // adaptadorRecycler.vaciarLista()
                adaptadorRecycler.agregarDato(
                    Asignaturas(
                        "DI",
                        "Borja",
                        6,
                        "DAM",
                        2,
                        R.drawable.angular
                    )
                )
            }
            //crear un menu adicional nuevo, condos opciones ADD y CLEAR
            // al dar add enun recyclerview va añadiendo prueba.
            // si se da al boton de vaciar se elimina todos.

        }
        return true
    }

    /*
    R.id.menuop_uno->{
       texto = "Pulsado elemento 1"
    }
    R.id.menuop_dos->{
        texto = "Pulsado elemento 2"
    }
    R.id.menuop_tres->{
        texto = "Pulsado elemento 3"
    }
    R.id.menuop_cuatro->{
        texto = "Pulsado elemento 4"
    }
    else->{
        texto = "Elemento desconocido"
    }
}
Snackbar.make(binding.root,texto,Snackbar.LENGTH_SHORT).show()
// Crear un menu con 7 opciones cada pulsacion muestra un Snackbar con el siguiente texto
// Pulsado el elemento {1-5}
// Pulsado elemento {100}
return true
*/
}