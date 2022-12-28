package com.example.t3_spinner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.t3_spinner.adapter.AdapterPais
import com.example.t3_spinner.databinding.ActivityMainBinding
import com.example.t3_spinner.model.Pais
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var  adapadtadorSimple: ArrayAdapter<Pais>
    // private lateinit var  adapadtadorSimple: ArrayAdapter<CharSequence>
    private lateinit var arrayDatos: ArrayList<Pais>
    private lateinit var adaptadorPais: AdapterPais
    /*
    lateinit var resultadoTexto: TextView;
    var numeroString:String= "2"
    var numeroNumero:Int= 2
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        /*
        resultadoTexto.text.toString().toInt() as Int
        numeroString.toInt()

        numeroNumero.toString()
         */
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //parte grafica binding.spinnerSimple
        // parte logica --> lista de datos(Array - ArrayList o un recurso), vista grafica
        //                  vista grafica --> android.R.layout.simple_spinner_item_1
        //                  contexto --> getAplicationContext
        instancias();
        asociasDatos();
        rellenarLista();
        acciones();
    }

    private fun acciones() {
        binding.spinnerSimple.onItemSelectedListener = this
        binding.spinnerComplejo.onItemSelectedListener = this
        binding.botonAgregar.setOnClickListener {
            // arrayDatos.add("Dato nuevo")
            adapadtadorSimple.notifyDataSetChanged()
        }
        binding.botonDetalle.setOnClickListener {
            var intent: Intent = Intent(applicationContext, DetailActivity::class.java);
            var bundle: Bundle = Bundle();
            bundle.putSerializable("equipo", adaptadorPais.getItem(binding.spinnerComplejo.selectedItemPosition))
        }
    }

    private fun rellenarLista() {
        /*
        arrayDatos.add("Elemento  uno");
        arrayDatos.add("Elemento  dos");
        arrayDatos.add("Elemento  tres");
        arrayDatos.add("Elemento  cuatro");
         */
        arrayDatos.add(Pais("España",R.drawable.espania,1,"Pedro"))
        arrayDatos.add(Pais("Brasil",R.drawable.brasil,5,"Neymar"))
        arrayDatos.add(Pais("Alemania",R.drawable.alemania,3,"Muller"))
        arrayDatos.add(Pais("Francia",R.drawable.francia,2,"Mbappe"))
        arrayDatos.add(Pais("Argentina",R.drawable.argentina,2,"MESSI"))
        arrayDatos.add(Pais("Qatar",R.drawable.qatar,0,"Desconocido"))
        adapadtadorSimple.notifyDataSetChanged()
        adaptadorPais.notifyDataSetChanged()


    }

    private fun asociasDatos() {
        binding.spinnerSimple.adapter = adapadtadorSimple;
        binding.spinnerComplejo.adapter = adaptadorPais;
    }

    private fun instancias() {
        arrayDatos = ArrayList();
        adaptadorPais = AdapterPais(arrayDatos,applicationContext);
        // entiendo que la ser un array son los datos de dentro preguntar simple spinner item
        adapadtadorSimple = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, arrayDatos)
        adapadtadorSimple.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // adapadtadorSimple = ArrayAdapter.createFromResource(applicationContext,R.array.array_recurso,android.R.layout.simple_spinner_item)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        // p0 --> vista del evento
        // p1 --> la fila que se ha pulsado
        // p2 --> la posicion pulsada 0..n
        // p3 --> id(pk): id --> posicion

        when(p0!!.id){
            R.id.spinner_simple->{
                binding.imagenEscudo.setImageResource(adaptadorPais.getItem(p2).getImagen())
                Snackbar.make(binding.spinnerSimple, adapadtadorSimple.getItem(p2)?.getJugadorEstrella() ?:"No hay datos", Snackbar.LENGTH_SHORT).show()
            }
            R.id.spinner_complejo->{
                binding.imagenEscudo.setImageResource(adaptadorPais.getItem(p2).getImagen())
            }
        }




        //Snackbar.make(p1!!, arrayDatos.get(p2), Snackbar.LENGTH_SHORT).show()
        // Snackbar.make(binding.spinnerSimple, adapadtadorSimple.getItem(p2) ?:"No hay datos", Snackbar.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }


}