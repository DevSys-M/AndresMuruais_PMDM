package com.example.t6_juegos

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.t6_juegos.databinding.ActivitySecondBinding
import com.example.t6_juegos.model.Juegos

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    lateinit var juegoRecuperado: Juegos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        juegoRecuperado = intent.extras?.getSerializable("juegos") as Juegos
    }
}