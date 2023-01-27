package com.example.t5_reservas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.t5_reservas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isntancias()
        rellenarDatos()
        acciones()
    }

    private fun isntancias() {

    }

    private fun rellenarDatos() {

    }

    private fun acciones() {

    }
}