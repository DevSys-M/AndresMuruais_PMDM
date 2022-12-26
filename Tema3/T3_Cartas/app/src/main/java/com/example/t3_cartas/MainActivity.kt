package com.example.t3_cartas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.t3_cartas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        acciones()
    }

    private fun acciones() {
        binding.botonEmpezar.setOnClickListener {
            if(binding.textoNombre.text.isNotEmpty()){
                var intent = Intent(applicationContext,SecondActivity::class.java)
                var bundle: Bundle = Bundle();
                bundle.putString("nombre",binding.textoNombre.text.toString())
                intent.putExtras(bundle)
                startActivity(intent)
            }else{
                Log.v("texto_nombre","Toast Ejecutado")
                Toast.makeText(applicationContext,"Texto vacio", Toast.LENGTH_SHORT).show()
            }
        }
    }
}