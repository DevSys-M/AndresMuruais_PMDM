package com.example.t7_ejercicio.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.t7_ejercicio.databinding.FragmentLoginBinding
import com.example.t7_ejercicio.model.Usuario
import com.google.android.material.snackbar.Snackbar

class FragmentLogin: Fragment() {

    private lateinit var binding: FragmentLoginBinding

    val listaUsuarios = ArrayList<Usuario>()
    // TODO 2. creo un objeto de la interfaz
    private lateinit var listener: OnFragmentNombreListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // TODO 3. inicio el objeto de la interfaz, pasada a la interfaz
        listener = context as OnFragmentNombreListener
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        listaUsuarios.add(Usuario("user","user"))
        listaUsuarios.add(Usuario("admin","admin"))
        listaUsuarios.add(Usuario("guest","guest"))
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.fragmentBotonLogin.setOnClickListener {
            if(binding.fragmentUsuario.text == listaUsuarios.filter { it.nombre.equals(binding.fragmentUsuario.text)}
                && binding.fragmentPass.text == listaUsuarios.filter { it.pass == it.nombre }){
                listener.onNombreSelected(binding.fragmentUsuario.text.toString())
            }
        }
    }
    //TODO 1. interfaz en el origen
    interface OnFragmentNombreListener{
        fun onNombreSelected(nombre: String)
    }
}


