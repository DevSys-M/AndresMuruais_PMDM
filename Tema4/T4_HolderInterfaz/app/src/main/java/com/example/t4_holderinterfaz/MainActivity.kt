package com.example.t4_holderinterfaz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.*
import com.example.t4_holderinterfaz.adapter.AdaptadorLenguajes
import com.example.t4_holderinterfaz.databinding.ActivityMainBinding
import com.example.t4_holderinterfaz.model.Lenguaje
import com.google.android.material.snackbar.Snackbar

//TODO EVENTO 3
//Por último se implementa la interfaz en la activity (destino de los datos, obligando así a tener que sobreescribir el método en dicha clase y por lo tanto el item que se ha pulsado estará disponible en la clase destino
class MainActivity : AppCompatActivity(), AdaptadorLenguajes.OnLenguajeListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var arrayLenguajes: ArrayList<Lenguaje>
    /*
     Una vez se tiene creado el adaptador, el último paso es irnos a la activity y juntar la parte gráfica (el recyclerView) con la parte de datos (adaptador).
     Para ello lo primero que tendremos que hacer es crear un objeto del tipo adaptador, utilizando el constructor al cual se le debe pasar un contexto y una lista (ya creada)
    */
    private lateinit var adaptadorLenguaje: AdaptadorLenguajes


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rellenarDatos()
        configurarRecycler()
        //acciones()
    }

    private fun acciones() {
        /*
        adaptadorLenguaje.onLenguajeClick = { lenguaje ->
            var snackbar: Snackbar = Snackbar.make(
                findViewById(R.id.recycler_lenguajes),
                "¿Quieres ver los dataller?",
                Snackbar.LENGTH_SHORT
            )
            snackbar.setAction("ok", View.OnClickListener { view: View ->

            })

            snackbar.show();
        }
         */
    }

    private fun rellenarDatos() {
        arrayLenguajes = ArrayList();
        arrayLenguajes.add(Lenguaje("Kotlin",1.7,"JetBrain",R.drawable.kotlin))
        arrayLenguajes.add(Lenguaje("Kotlin",1.7,"JetBrain",R.drawable.kotlin))
        arrayLenguajes.add(Lenguaje("Kotlin",1.7,"JetBrain",R.drawable.kotlin))
        arrayLenguajes.add(Lenguaje("Kotlin",1.7,"JetBrain",R.drawable.kotlin))
        arrayLenguajes.add(Lenguaje("Kotlin",1.7,"JetBrain",R.drawable.kotlin))
        arrayLenguajes.add(Lenguaje("Kotlin",1.7,"JetBrain",R.drawable.kotlin))
        arrayLenguajes.add(Lenguaje("Kotlin",1.7,"JetBrain",R.drawable.kotlin))
        arrayLenguajes.add(Lenguaje("Kotlin",1.7,"JetBrain",R.drawable.kotlin))
        adaptadorLenguaje = AdaptadorLenguajes(this, arrayLenguajes)
        adaptadorLenguaje.notifyDataSetChanged()
    }
    //Por último para poder asociar adaptador con recycler se debe utilizar el método setAdapter (para indicar el adaptador) y el método setLayout (para indicar como se muestran los datos)
    private fun configurarRecycler() {
        binding.recyclerLenguajes.adapter = adaptadorLenguaje
        binding.recyclerLenguajes.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

       //GridLayout: única que los elementos se verán en formato cuadrícula, indicando el número de columnas que se quieren utilizar
       // binding.recyclerLenguajes.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        // es el decorador (elemento que se sitúa entre dos filas). Si no configuramos nada el decorador aparecerá en blanco, pero si se quiere utilizar uno sería necesario utilizar el método addItemDecoration
        binding.recyclerLenguajes.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL))

        //la de animación, la cual se activará cada vez que un elemento sea agregado, eliminado, etc... Para ello se utiliza el método setItemAnimator
        binding.recyclerLenguajes.itemAnimator = DefaultItemAnimator()
    }
    //TODO EVENTO 3
    override fun onLenguajeClick(lenguaje: Lenguaje) {
        Log.v("prueba", lenguaje.nombre)

    }

}