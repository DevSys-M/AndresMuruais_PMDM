package com.example.t7_ejercicio.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.t7_ejercicio.R
import com.example.t7_ejercicio.databinding.FragmentHeaderBinding
import com.example.t7_ejercicio.model.Usuario
import com.google.android.material.snackbar.Snackbar

class FragmentHeader: Fragment() {

    private lateinit var binding: FragmentHeaderBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentHeaderBinding.inflate(inflater,container,false)
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbarItem)
        activity.supportActionBar?.title="Login"

        return binding.root
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_salir->{
                Snackbar.make(binding.root,"Saliendo",Snackbar.LENGTH_SHORT).show()
            }
        }
        return true
    }

}