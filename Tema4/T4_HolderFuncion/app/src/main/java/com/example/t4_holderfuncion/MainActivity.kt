package com.example.t4_holderfuncion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t4_holderfuncion.adapter.AdaptadorLenguajes
import com.example.t4_holderfuncion.databinding.ActivityMainBinding
import com.example.t4_holderfuncion.model.Lenguaje
import com.google.android.material.snackbar.Snackbar

/*
Por último en el MainActivity no sería necesario implementar nada
ya que no hemos creado una interfaz sino que hemos realizado una función, la cual es llamada desde otra función.
 */
class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private lateinit var arrayLenguajes: ArrayList<Lenguaje>
    private lateinit var adaptadorLenguaje: AdaptadorLenguajes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rellenarDatos()
        configurarRecycler()
        acciones()
        //TODO BOTONES
        //Un botón en MainActivity que al ser pulsado agregará un lenguaje por defecto a la lista de ya existentes (y por lo tanto se agregará al recycler)
        //Que tras una pulsación larga en el nombre del lenguaje de la lista, aparezca una snackbar preguntando si queremos eliminar el lenguaje de la lista.
    }
    //Como se puede ver en el método acciones
    //la función creada en el adaptador es llamada, teniendo como parámetros el lenguaje que ha sido seleccionado en el listado.
    private fun acciones() {
        adaptadorLenguaje.onLenguajeClick = { lenguaje ->
            var snackbar: Snackbar = Snackbar.make(
                binding.recyclerLenguajes,
                "¿Quieres ver los datalles?",
                Snackbar.LENGTH_SHORT
            )
            snackbar.setAction("ok", View.OnClickListener { view: View ->
                var intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("lenguaje", lenguaje);
                startActivity(intent)
            })
            snackbar.show();
        }
        //TODO BOTONES 1
        binding.buttonAgregar.setOnClickListener {
            var lenguaje: Lenguaje =
                Lenguaje("Por Defecto", 1.0, "detalle del lenguaje por defecto", 0)
            arrayLenguajes.add(lenguaje);
            //adaptadorLenguaje.notifyDataSetChanged()
            /*
            Con esto sería suficiente para poder añadir elementos al recyclerview.
            Sin embargo el método notifyDataSetChanged es demasiado potente, ya que notifica un cambio estructural de todos los elementos.
            Podríamos detectar la posición del cambio e indicar al método que elemento ha cambiado, siendo esta caso el último
             */
            adaptadorLenguaje.notifyItemInserted(arrayLenguajes.size-1)
            // En este caso se ha hecho desde la clase MainActivity, pero también se podría haber hecho un método en el adaptador y ser llamado desde la clase MainActivity
        }
        //TODO BOTONES 2
        adaptadorLenguaje.onNombreLongClick = { posicion ->
            arrayLenguajes.removeAt(posicion)
            adaptadorLenguaje.notifyItemRemoved(posicion)
        }
        /*
        adaptadorLenguaje.onLenguajeClick = { lenguaje ->
            Log.v("prueba", lenguaje.nombre)
        }
         */
    }

    private fun configurarRecycler() {
        binding.recyclerLenguajes.adapter = adaptadorLenguaje
        binding.recyclerLenguajes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerLenguajes.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.HORIZONTAL
            )
        )
        binding.recyclerLenguajes.itemAnimator = DefaultItemAnimator()
    }

    private fun rellenarDatos() {

        arrayLenguajes = ArrayList();
        arrayLenguajes.add(Lenguaje("Kotlin", 1.7, "JetBrain", R.drawable.kotlin))
        arrayLenguajes.add(Lenguaje("Kotlin", 1.7, "JetBrain", R.drawable.kotlin))
        arrayLenguajes.add(Lenguaje("Kotlin", 1.7, "JetBrain", R.drawable.kotlin))
        arrayLenguajes.add(Lenguaje("Kotlin", 1.7, "JetBrain", R.drawable.kotlin))
        arrayLenguajes.add(Lenguaje("Kotlin", 1.7, "JetBrain", R.drawable.kotlin))
        arrayLenguajes.add(Lenguaje("Kotlin", 1.7, "JetBrain", R.drawable.kotlin))
        arrayLenguajes.add(Lenguaje("Kotlin", 1.7, "JetBrain", R.drawable.kotlin))
        arrayLenguajes.add(Lenguaje("KotlinÚltimo", 1.7, "JetBrain", R.drawable.kotlin))
        adaptadorLenguaje = AdaptadorLenguajes(this, arrayLenguajes)

    }


}