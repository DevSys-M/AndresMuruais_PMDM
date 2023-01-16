package com.example.t4_conversor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.t4_conversor.databinding.ActivityMainBinding
import com.example.t4_conversor.dialog.DialogoPersonalizado

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        acciones()
    }

    private fun acciones() {
        binding.botonConversor.setOnClickListener {
            DialogoPersonalizado().show(supportFragmentManager,"")
        }
    }
}
