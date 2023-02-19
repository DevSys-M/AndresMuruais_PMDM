package com.example.t6_menudialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t6_menudialog.adapter.AdaptadorAsignaturas
import com.example.t6_menudialog.databinding.ActivityMainBinding
import com.example.t6_menudialog.dialogs.DialogoAdd
import com.example.t6_menudialog.dialogs.DialogoDetalle
import com.example.t6_menudialog.model.Asignatura
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), DialogoAdd.OnDialogoAdd, AdaptadorAsignaturas.OnRecyclerAsignaturaListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adaptador: AdaptadorAsignaturas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        // cambios en el toolbar
        supportActionBar?.title = "Cambio en el titulo"
        adaptador = AdaptadorAsignaturas(ArrayList<Asignatura>(),this)

        // binding.recyclerAsignaturas --> grafico
        // Adapter
        binding.recyclerAsignaturas.adapter = adaptador;
        binding.recyclerAsignaturas.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL,false)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_recycler, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_add -> {
                DialogoAdd().show(supportFragmentManager,"")
                // agregar algo al recycler --> adaptador
                // adaptador.addAsignatura("Prueba")
            }
            R.id.menu_clear -> {
                 adaptador.clearAsignaturas()
            }
        }




        return true
    }

    override fun onAsignaturaSelected(asignatura: Asignatura) {
        adaptador.addAsignatura(asignatura)
    }

    override fun onAsignaturaRecyclerSelected(asignatura: Asignatura) {

        DialogoDetalle.newInstance(asignatura).show(supportFragmentManager,"")
    }


}