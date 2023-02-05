package com.example.t6_juegos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.t6_juegos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Listado Juegos"
        setSupportActionBar(binding.toolbar)
    }
}