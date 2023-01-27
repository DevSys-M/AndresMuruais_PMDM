package com.example.t5_dialogscons

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import com.example.t5_dialogscons.databinding.ActivityMainBinding
import com.example.t5_dialogscons.dialogs.*
import com.example.t5_dialogscons.dialogs.DialogoConfirmacion.Companion.newInstance
import com.example.t5_dialogscons.model.Usuario

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener,
 DialogoPerso.OnUsuarioListener, DialogoConfirmacion.OnDialogoListener,DialogoMultiple.OnMultipleListener,
DialogoPersoNota.OnNotaListener{
    private lateinit var binding: ActivityMainBinding
    private lateinit var hora: String
    private lateinit var minutos: String
    private lateinit var dia: String
    private lateinit var mes: String
    private lateinit var anio:String
    private lateinit var nombre: String
    private lateinit var apellido: String
    private lateinit var nota: String
    private lateinit var elementos: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonEmpezar.setOnClickListener {
            DialogoHora().show(supportFragmentManager,null)
        }
    }



    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        hora = hourOfDay.toString()
        minutos = minute.toString()
        DialogoFecha().show(supportFragmentManager,null)
    }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        anio = year.toString()
        mes = (month+1).toString()
        dia = dayOfMonth.toString()
        DialogoPerso().show(supportFragmentManager,null)
    }

    override fun usuarioSelected(usuario: Usuario) {
        nombre = usuario.nombre.toString()
        apellido = usuario.apellido.toString()
        DialogoConfirmacion.newInstance(hora,minutos,dia,mes,anio,nombre).show(supportFragmentManager,null)

    }

    override fun onDialogoSelected(boolean: Boolean) {
        if (boolean){
            DialogoMultiple().show(supportFragmentManager,null)
        }
    }

    override fun onMultipleSelected(lista: ArrayList<String>) {
        elementos= ArrayList()
        elementos = lista
        lista.forEach { elemento->Log.v("asignatura",elemento) }
        DialogoPersoNota().show(supportFragmentManager,null)

    }

    override fun notaSelected(mensaje: String) {
        nota = mensaje
        DialogoPersoResumen.newInstance(hora,minutos,dia,mes,anio,nombre,elementos,nota).show(supportFragmentManager,null)
    }

}