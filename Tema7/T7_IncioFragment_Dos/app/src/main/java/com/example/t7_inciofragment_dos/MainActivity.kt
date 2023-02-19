package com.example.t7_inciofragment_dos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.t7_inciofragment_dos.databinding.ActivityMainBinding
import com.example.t7_inciofragment_dos.fragments.FragmentDos
import com.example.t7_inciofragment_dos.fragments.FragmentUno
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), View.OnClickListener, FragmentUno.OnFragmentNombreListener {


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.botonF1.setOnClickListener( this)
        binding.botonF2.setOnClickListener( this)
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            binding.botonF1.id ->{
                val ft = supportFragmentManager.beginTransaction();
                ft.replace(binding.sitioFragments.id,FragmentUno())
                ft.commit()
            }
            binding.botonF2.id ->{

                val ft = supportFragmentManager.beginTransaction();
                ft.replace(binding.sitioFragments.id, FragmentDos())
                ft.commit()
            }
            binding.botonF3.id ->{

                val fm = supportFragmentManager

            }
        }
    }


    override fun onNombreSelected(nombre: String) {
        // TODO 4. Ejecuto el cambio de fragment, con newInstance
        val fm = supportFragmentManager;
        val ft = fm.beginTransaction();
        ft.replace(binding.sitioFragments.id,FragmentDos.newInstance(nombre))
        ft.commit()
        Snackbar.make(binding.root,nombre,Snackbar.LENGTH_SHORT).show()
    }
}