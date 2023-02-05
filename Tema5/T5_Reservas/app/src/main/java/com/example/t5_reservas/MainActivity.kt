package com.example.t5_reservas

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.LinearLayout
import android.widget.TimePicker
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t5_reservas.adapters.AdapterRecycler
import com.example.t5_reservas.adapters.AdapterSpinner
import com.example.t5_reservas.databinding.ActivityMainBinding
import com.example.t5_reservas.dialogs.DialogoDetalles
import com.example.t5_reservas.dialogs.DialogoFecha
import com.example.t5_reservas.dialogs.DialogoHora
import com.example.t5_reservas.model.Ciudades
import com.example.t5_reservas.model.Reservas
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener,
    AdapterRecycler.OnRecyclerListener {
    lateinit var binding: ActivityMainBinding

    private lateinit var arrayCiudad: ArrayList<Ciudades>
    private lateinit var adaptadorCiudad: AdapterSpinner

    private lateinit var adapterRecycler: AdapterRecycler

    private var horaO: Int = 0
    private var horaD: Int = 0
    private var minutosO: Int = 0
    private var minutosD: Int = 0
    private var diaO: Int = 0
    private var diaD: Int = 0
    private var mesO: Int = 0
    private var mesD: Int = 0
    private var anioO: Int = 0
    private var anioD: Int = 0
    private var origen = false

    private lateinit var ciudadOrigen: ArrayList<Ciudades>
    private lateinit var ciudadDestino: Ciudades
    private lateinit var textoOrigen: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //spinner
        binding.spinnerOrigen.adapter = ArrayAdapter.createFromResource(
            applicationContext,
            R.array.ciudades,
            android.R.layout.simple_spinner_item
        )
        (binding.spinnerOrigen.adapter as ArrayAdapter<CharSequence>).setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        //spinner
        arrayCiudad = ArrayList()
        arrayCiudad.add(Ciudades(R.drawable.londres, "Londres"))
        arrayCiudad.add(Ciudades(R.drawable.barcelona, "Barcelona"))
        arrayCiudad.add(Ciudades(R.drawable.madrid, "Madrid"))
        arrayCiudad.add(Ciudades(R.drawable.miami, "Miami"))
        arrayCiudad.add(Ciudades(R.drawable.newyork, "New York"))
        arrayCiudad.add(Ciudades(R.drawable.sanfrancisco, "San Francisco"))
        adaptadorCiudad = AdapterSpinner(arrayCiudad, applicationContext)
        binding.spinnerDestino.adapter = adaptadorCiudad


        binding.spinnerOrigen.onItemSelectedListener = this
        binding.spinnerDestino.onItemSelectedListener = this
        //dialogos
        binding.textoOrigen.setOnClickListener {
            DialogoFecha().show(supportFragmentManager, null)
        }
        binding.textoDestino.setOnClickListener {
            DialogoFecha.newInstance(diaO, mesO, anioO).show(supportFragmentManager, null)
        }
        //Recycler
        adapterRecycler = AdapterRecycler(ArrayList<Reservas>(), this)
        binding.recycler.adapter = adapterRecycler
        binding.recycler.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        //boton
        binding.botonAdd.setOnClickListener {
            adapterRecycler.addReserva(
                Reservas(
                    ciudadOrigen.toString(),
                    ciudadDestino.imagen,
                    ciudadDestino.texto,
                    ciudadDestino.imagen,
                    "${diaO}/${mesO}/${anioO}",
                    "${horaO}:${minutosO}"
                )
            )
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        DialogoHora().show(supportFragmentManager, null)
        diaO = dayOfMonth
        mesO = month
        anioO = year

        diaD = dayOfMonth
        mesD = month
        anioD = year


    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        if (!origen) {
            horaO = hourOfDay
            minutosO = minute
            binding.textoOrigen.text = "${diaO}/${mesO}/${anioO} ${horaO}:${minutosO}"
            origen = true
        } else if (origen) {
            horaD = hourOfDay
            minutosD = minute
            binding.textoDestino.text = "${diaD}/${mesD}/${anioD} ${horaD}:${minutosD}"
            origen = false
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent!!.id) {
            binding.spinnerOrigen.id -> {
                textoOrigen = binding.spinnerOrigen.adapter.getItem(position).toString()
                Log.v("origen",textoOrigen)
                ciudadOrigen = arrayCiudad.filter { it.texto == textoOrigen } as ArrayList<Ciudades>
                /*
                when(position){
                    0->{
                        ciudadOrigen = arrayCiudad.filter { it.texto == "Londres" } as Ciudades
                    }
                    1->{
                        ciudadOrigen = arrayCiudad.filter { it.texto == "Barcelona" } as Ciudades
                    }
                    2->{
                        ciudadOrigen = arrayCiudad.filter { it.texto == "Madrid" } as Ciudades
                    }
                    3->{
                        ciudadOrigen = arrayCiudad.filter { it.texto == "Miami" } as Ciudades
                    }
                    4->{
                        ciudadOrigen = arrayCiudad.filter { it.texto == "New York" } as Ciudades
                    }
                    5->{
                        ciudadOrigen = arrayCiudad.filter { it.texto == "San Francisco" } as Ciudades
                    }
                } */

            }
            binding.spinnerDestino.id -> {
                ciudadDestino = binding.spinnerDestino.adapter.getItem(position) as Ciudades
                Log.v("destino",ciudadDestino.toString())
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onRecyclerSelected(reservas: Reservas) {
        DialogoDetalles.newInstance(reservas).show(supportFragmentManager, null)
    }


}