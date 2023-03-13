package com.example.t8_simulacro

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.t8_simulacro.databinding.ActivitySecondBinding
import com.google.firebase.database.FirebaseDatabase

class SecondActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivitySecondBinding
    private lateinit var dataBase: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        dataBase = FirebaseDatabase.getInstance("https://pdpm-project-default-rtdb.europe-west1.firebasedatabase.app/")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        /*
        val uid = intent.extras!!.getString("uid")
        val nombre = intent.extras!!.getString("nombre")
        val edad = intent.extras!!.getInt("edad")

        val referencia = dataBase.getReference("usuarios").child(uid!!)

        referencia.child("datos").child("nombre").setValue(nombre)
        referencia.child("datos").child("edad").setValue(edad)

         */

        val uid = intent?.extras?.getString("uid")
        if (uid != null) {
            val nombre = intent.extras!!.getString("nombre")
            val edad = intent.extras!!.getInt("edad")

            val referencia = dataBase.getReference("usuarios").child(uid)

            referencia.child("datos").child("nombre").setValue(nombre)
            referencia.child("datos").child("edad").setValue(edad)
        }
        setContentView(binding.root)



        setSupportActionBar(binding.toolbar)
        val navController = findNavController(R.id.nav_host_fragment_content_second)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.menu_second,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_ligas->{
            }
            R.id.action_fav->{

            }
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_second)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}