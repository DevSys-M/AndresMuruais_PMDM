package com.example.t4_coches

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.t4_coches.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instancias()
        rellenarDatos()
        asociarDatos()
        acciones()
    }

    private fun acciones() {
        TODO("Not yet implemented")
    }

    private fun asociarDatos() {
        TODO("Not yet implemented")
    }

    private fun rellenarDatos() {
        TODO("Not yet implemented")
    }

    private fun instancias() {
        TODO("Not yet implemented")
    }
}