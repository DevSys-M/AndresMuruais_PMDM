package com.example.t7_inciofragment_dos.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.t7_inciofragment_dos.databinding.FragmentHeaderBinding

class FragmentHeader: Fragment() {

    private lateinit var binding: FragmentHeaderBinding;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeaderBinding.inflate(inflater,container,false)
        return binding.root;
    }

    override fun onStart() {
        super.onStart()
        binding.botonHeader.setOnClickListener {
            //Snackbar.make(binding.root,"Primera app con fragments",Snackbar.LENGTH_SHORT).show()
            crearDialogoAyuda().show()
        }

    }


    fun crearDialogoAyuda(): AlertDialog {
        val builder : AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Primera aplicacion con fragments")
        return builder.create();

    }

}