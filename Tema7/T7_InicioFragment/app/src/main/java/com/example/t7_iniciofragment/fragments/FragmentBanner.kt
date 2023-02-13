package com.example.t7_iniciofragment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.t7_iniciofragment.databinding.FragmentBannerBinding

class FragmentBanner:Fragment() {

    //al pulsar el boton un cuadro de dialgo de confimacion donde ponga:

    // priemera app de fragment realizada por  XXXX

    private lateinit var binding: FragmentBannerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBannerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.botonStatico.setOnClickListener {
            lanzarDialogoAyuda().show()
        }
    }

    fun lanzarDialogoAyuda():AlertDialog{
        val builder:AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Dialogo ayuda")
        builder.setMessage(" priemera app de fragment realizada por Andres")
        return builder.create()
    }
}