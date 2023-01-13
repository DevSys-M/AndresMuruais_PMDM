package com.example.t5_dialogos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.example.t5_dialogos.databinding.ActivityMainBinding
import com.example.t5_dialogos.dialogs.DialogoConfirmacion
import com.example.t5_dialogos.dialogs.DialogoSelecion
import com.example.t5_dialogos.dialogs.DialogoSimple

//implemetar la interfaz
class MainActivity : AppCompatActivity(), OnClickListener, DialogoConfirmacion.OnDialogoListener,DialogoSelecion.onDialogoListaListener,DialogoSimple.OnDialogoSimpleListener{
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonConfirmacion.setOnClickListener(this)
        binding.botonListas.setOnClickListener(this)
        binding.botonSimple.setOnClickListener(this)
        binding.botonMultiple.setOnClickListener(this)
        binding.botonPersonalizado.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            binding.botonConfirmacion.id->{
                //1.objeto del dialogo--> la clase
                // metodo show
                DialogoConfirmacion().show(supportFragmentManager,null)
            }
            binding.botonListas.id->{
                DialogoSelecion().show(supportFragmentManager,null)
            }
            binding.botonSimple.id->{
                DialogoSimple().show(supportFragmentManager,null)
            }
        }
    }

    override fun onDialogoSelected(mensaje: String) {
        binding.textoConfirmacion.text = mensaje
    }

    override fun onDialogoListaSelected(mensaje: String) {
        binding.textoListas.text = mensaje
    }
    override fun onDialogoSimpleSelected(mensaje: String) {
        binding.textoSimple.text = mensaje
    }

}