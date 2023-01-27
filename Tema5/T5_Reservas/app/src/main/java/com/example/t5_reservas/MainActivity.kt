package com.example.t5_reservas

import android.R
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import com.example.t5_reservas.databinding.ActivityMainBinding
import com.example.t5_reservas.dialogs.DialogoFecha
import com.example.t5_reservas.dialogs.DialogoHora

class MainActivity : AppCompatActivity(),DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private lateinit var binding: ActivityMainBinding

    private var horaO: Int =0
    private  var horaD: Int =0
    private  var minutosO: Int =0
    private  var minutosD: Int =0
    private  var diaO: Int =0
    private  var diaD: Int =0
    private  var mesO: Int =0
    private  var mesD: Int =0
    private  var anioO: Int =0
    private  var anioD: Int =0
    private  var nombreO: Int =0
    private  var nombreD: Int =0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rellenarDatos()
        acciones()
    }


    private fun rellenarDatos() {
        val ciudades = arrayOf("Londres","Madrid","Miami","NewYork","San Francisco");
        val adapterorigen = ArrayAdapter(this, R.layout.simple_list_item_1,ciudades)
        val adapterdestino =  ArrayAdapter(this, R.layout.simple_list_item_1,ciudades)
        adapterorigen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterdestino.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerOrigen.adapter = adapterorigen
        binding.spinnerDestino.adapter = adapterdestino
    }

    private fun acciones() {
     binding.textoOrigen.setOnClickListener{
         DialogoFecha().show(supportFragmentManager,null)


     }

        binding.textoDestino.setOnClickListener{
            Log.v("dia",diaO.toString())
            Log.v("mes",mesO.toString())
            Log.v("anio",mesO.toString())
            DialogoFecha.newInstance(diaO,mesO,mesO).show(supportFragmentManager,null)

        }



    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        DialogoHora().show(supportFragmentManager,null)
        diaO = dayOfMonth
        mesO = month
        anioO = year

        diaD= dayOfMonth
        mesD = month
        anioD = year

    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        horaO = hourOfDay
        minutosO = minute
        binding.textoOrigen.setText("${diaO}/${mesO}/${anioO} ${horaO}:${minutosO}")
        if (horaO>0){
            horaD= hourOfDay
            minutosD = minute
            binding.textoDestino.setText("${diaD}/${mesD}/${anioD} ${horaD}:${minutosD}")
        }
    }
}