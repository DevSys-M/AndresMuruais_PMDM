package com.example.t7_iniciofragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.example.t7_iniciofragment.databinding.ActivityMainBinding
import com.example.t7_iniciofragment.fragments.FragmentBanner
import com.example.t7_iniciofragment.fragments.FragmentDetalle
import com.example.t7_iniciofragment.fragments.FragmentNombre

class MainActivity : AppCompatActivity(), OnClickListener,FragmentNombre.OnFragmentNombre {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.botonF1.setOnClickListener(this)
        binding.botonF2.setOnClickListener(this)
        binding.botonF3.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
       val ft = supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
       when(v!!.id){
           binding.botonF1.id->{
              // val fm = supportFragmentManager
               //val ft = fm.beginTransaction()

               ft.replace(binding.sitioFragments.id,FragmentNombre())
               ft.addToBackStack("")
               ft.commit()
           }
           binding.botonF2.id->{
               ft.replace(binding.sitioFragments.id, FragmentDetalle())
               ft.commit()

           }
           binding.botonF3.id->{

           }
       }
    }

    override fun onNombreSelect(nombre: String) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(binding.sitioFragments.id, FragmentDetalle.newInstance(nombre))
        ft.addToBackStack("")
        ft.commit()
    }
}