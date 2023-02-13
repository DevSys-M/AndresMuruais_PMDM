package com.example.t7_iniciofragment.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.t7_iniciofragment.databinding.FragmentDetalleBinding

class FragmentDetalle: Fragment() {

    private lateinit var binding: FragmentDetalleBinding
    private lateinit var nombre: String

    companion object{
        fun newInstance(nombre: String): FragmentDetalle{
            val args = Bundle()
            args.putString("nombre",nombre)
            val fragment = FragmentDetalle()
            fragment.arguments = args
            return fragment
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        nombre = this.arguments?.getString("nombre")?: "asadsa"

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetalleBinding.inflate(inflater,container,false)
        return binding.root
    }

}