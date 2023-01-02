package com.example.t3_hobbies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.t3_hobbies.adapter.AdapterHobbies
import com.example.t3_hobbies.databinding.ActivitySecondBinding
import com.example.t3_hobbies.model.Hobbies

class SecondActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var adaptadorSimple: ArrayAdapter<CharSequence>
    private lateinit var listaHobbies: ArrayList<Hobbies>
    private lateinit var listaFutbol: ArrayList<Hobbies>
    private lateinit var listaJuegos: ArrayList<Hobbies>
    private lateinit var listaSeries: ArrayList<Hobbies>
    private lateinit var adaptadorLista: AdapterHobbies
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instancias()
        rellenarLista()
        asociarDatos()
        acciones()
    }

    private fun acciones() {
        binding.spinnerSimple.onItemSelectedListener = this

        binding.listaHobbies.setOnItemClickListener { adapterView, view, i, l ->
            binding.textoNombre.setText( adaptadorLista.getItem(i).toString())
            /*
            binding.textoNombre.setText( adaptadorLista.getItem(i).getNombre())
            binding.textoDetalle.setText(adaptadorLista.getItem(i).getDetalle())
            binding.imagenHobbie.setImageResource(adaptadorLista.getItem(i).getImagen())
             */
        }

    }

    private fun rellenarLista() {
        listaHobbies = ArrayList()
        listaHobbies.add(Hobbies("Messi", "FC. Barcelona", R.drawable.messi,"Futbol"))
        listaHobbies.add(Hobbies("Ronaldo", "Brasil", R.drawable.ronaldo,"Futbol"))
        listaHobbies.add(Hobbies("Maradona", "Argentina", R.drawable.maradona,"Futbol"))
        listaHobbies.add(Hobbies("Zidane", "Francia", R.drawable.zidane,"Futbol"))
        listaHobbies.add(Hobbies("Metal Gear", "Sigilo", R.drawable.metal,"Juegos"))
        listaHobbies.add(Hobbies("Gran Turismo", "Coches", R.drawable.gt,"Juegos"))
        listaHobbies.add(Hobbies("God Of War", "Plataformas", R.drawable.god,"Juegos"))
        listaHobbies.add(Hobbies("Final Fantasy X", "Rol", R.drawable.ffx,"Juegos"))
        listaHobbies.add(Hobbies("Stranger Things", "Fantastica", R.drawable.stranger,"Series"))
        listaHobbies.add(Hobbies("Juego de tronos", "Histórica", R.drawable.tronos,"Series"))
        listaHobbies.add(Hobbies("Lost", "Fantastica", R.drawable.lost,"Series"))
        listaHobbies.add(Hobbies("La casa de papel", "Accion", R.drawable.papel,"Series"))
        adaptadorLista= AdapterHobbies(listaHobbies,applicationContext)
        adaptadorLista.notifyDataSetChanged()
    }

    private fun asociarDatos() {
        binding.spinnerSimple.adapter = adaptadorSimple
        binding.listaHobbies.adapter = adaptadorLista
        adaptadorSimple.notifyDataSetChanged()
        adaptadorLista.notifyDataSetChanged()

    }

    private fun instancias() {
        adaptadorSimple = ArrayAdapter.createFromResource(applicationContext, R.array.spinner_recursos, android.R.layout.simple_spinner_item)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (adaptadorSimple.getItem(p2) == "Futbol") {
            listaFutbol = ArrayList()
            listaFutbol = listaHobbies.filter { it.getCategoria() == "Futbol" } as ArrayList<Hobbies>
            adaptadorLista = AdapterHobbies(listaFutbol, applicationContext)
            binding.listaHobbies.adapter = adaptadorLista
        }
        else if(adaptadorSimple.getItem(p2) == "Series"){
            listaSeries = ArrayList()
            listaSeries = listaHobbies.filter { it.getCategoria() == "Series" } as ArrayList<Hobbies>
            adaptadorLista = AdapterHobbies(listaSeries, applicationContext)
            binding.listaHobbies.adapter = adaptadorLista

        }
        else if(adaptadorSimple.getItem(p2) == "Juegos"){
            listaJuegos = ArrayList()
            listaJuegos = listaHobbies.filter { it.getCategoria() == "Juegos" } as ArrayList<Hobbies>
            adaptadorLista = AdapterHobbies(listaJuegos, applicationContext)
            binding.listaHobbies.adapter = adaptadorLista
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}