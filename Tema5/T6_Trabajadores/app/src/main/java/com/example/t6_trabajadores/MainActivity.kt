package com.example.t6_trabajadores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.t6_trabajadores.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instancias()
        rellenarDatos()
        acciones()
    }

    private fun instancias() {
        TODO("Not yet implemented")
    }

    private fun rellenarDatos() {
        TODO("Not yet implemented")
    }

    private fun acciones() {
        TODO("Not yet implemented")
    }
}