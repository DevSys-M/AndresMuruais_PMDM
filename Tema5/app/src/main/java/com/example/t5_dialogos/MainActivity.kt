package com.example.t5_dialogos

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t5_dialogos.adapter.AdaptadorUsuario
import com.example.t5_dialogos.databinding.ActivityMainBinding
import com.example.t5_dialogos.dialogs.*
import com.example.t5_dialogos.model.Usuario
import com.google.android.material.snackbar.Snackbar

//implemetar la interfaz
class MainActivity : AppCompatActivity(), OnClickListener, AdaptadorUsuario.OnRecyclerListener,
    DialogoConfirmacion.OnDialogoListener, DialogoSelecion.onDialogoListaListener,DialogoSimple.OnDialogoSimpleListener,DialogoPerso.OnRecyclerUsuariosListener,
    DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {
     private lateinit var binding: ActivityMainBinding

    private lateinit var adapterRecycler: AdaptadorUsuario
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterRecycler = AdaptadorUsuario(this, ArrayList<Usuario>())
        binding.recyclerUsuarios.adapter = adapterRecycler;
        binding.recyclerUsuarios.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        binding.botonConfirmacion.setOnClickListener(this)
        binding.botonListas.setOnClickListener(this)
        binding.botonSimple.setOnClickListener(this)
        binding.botonMultiple.setOnClickListener(this)
        binding.botonPersonalizado.setOnClickListener(this)

        binding.botonHora.setOnClickListener(this)
        binding.botonFecha.setOnClickListener(this)

        binding.botonComunica.setOnClickListener(this)

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
            binding.botonPersonalizado.id->{
                DialogoPerso().show(supportFragmentManager,null)
            }
            binding.botonMultiple.id->{
                DialogoMultiple().show(supportFragmentManager,null)
            }
            binding.botonHora.id -> {
                DialogoHora().show(supportFragmentManager, null)
            }
            binding.botonFecha.id->{
                DialogoFecha().show(supportFragmentManager,null)
            }
            binding.botonComunica.id->{
              DialogoComunica.newInstance("Andy","Murray").show(supportFragmentManager,"")

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

    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        //p1 hora
        //p2 minutos
        binding.textoHora.text = "Hora:${p1} Minutos: ${p2}"
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        // p1 año
        // p2 mes
        // p3 dia
        binding.textoFecha.text = "Dia: ${p3} Mes:${p2+1} Año: ${p1}"
    }

    override fun onRecyclerSelected(usuario: Usuario) {
        Snackbar.make(binding.root,"${usuario.pass} ${usuario.recordad}", Snackbar.LENGTH_SHORT).show()
    }

    override fun usuarioSelected(usuario: Usuario) {
        adapterRecycler.addUser(usuario)
    }

}