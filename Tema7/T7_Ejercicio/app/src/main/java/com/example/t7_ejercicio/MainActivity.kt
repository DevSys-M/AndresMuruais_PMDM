package com.example.t7_ejercicio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.t7_ejercicio.databinding.ActivityMainBinding
import com.example.t7_ejercicio.fragments.FragmentHome
import com.example.t7_ejercicio.fragments.FragmentLogin
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(),FragmentLogin.OnFragmentNombreListener {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fm = supportFragmentManager;
        val ft = fm.beginTransaction();
        ft.replace(binding.sitioFragments.id,FragmentLogin())
        ft.commit()

    }
    override fun onNombreSelected(nombre: String) {
        // TODO 4. Ejecuto el cambio de fragment, con newInstance
        val fm = supportFragmentManager;
        val ft = fm.beginTransaction();
        ft.replace(binding.sitioFragments.id, FragmentHome())
        ft.commit()
        Snackbar.make(binding.root,nombre, Snackbar.LENGTH_SHORT).show()
    }
}