package com.example.t3_estados_botones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.t3_estados_botones.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener, TextWatcher {
    lateinit var binding: ActivityMainBinding
    /*
     private lateinit var botonUno: Button;
     private lateinit var botonDos: Button;
     private var botonTres: Button ? = null;
     private var botonCuatro: Button ? = null;
     private lateinit var editNumeros: EditText;
  */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //        frameEjemplo = findViewById(R.id.boton_uno_uno);
        //        binding.editNumeros.append(frameEjemplo.tag as String);
        /*
        restriciones de girar el movil
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        otro modo es poner en AndroidManifest.xml en la estiqueta activity
        android:screenOrientation="portrait" o android:screenOrientation="portrait"
        if(resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_main)
        } else if (resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_warning)
        }
         */
        //instancias()
        acciones()
    }
    /*private fun instancias() {
    botonUno = findViewById(R.id.boton_uno)
    botonDos = findViewById(R.id.boton_dos)
    botonTres = findViewById(R.id.boton_tres)
    botonCuatro = findViewById(R.id.boton_cuatro)
    editNumeros = findViewById(R.id.edit_numeros)
}*/

    private fun acciones() {
        binding.botonUno?.setOnClickListener(this)
        binding.botonDos?.setOnClickListener(this)
        binding.botonTres?.setOnClickListener(this)
        binding.botonCuatro?.setOnClickListener(this)
        /*binding.editNumeros.addTextChangedListener { editable ->
            run {
                Log.v(
                    "texto",
                    editable.toString()
                )
            }
        }*/
        binding.editNumeros.addTextChangedListener(this)

        //binding.editNumeros.addText
    }

    override fun onClick(p0: View?) {
        binding.editNumeros.append((p0 as Button).text)
        /*when(p0!!.id){
                R.id.boton_uno->{
                    editNumeros.append("1")
                }
                R.id.boton_dos->{
                    editNumeros.append("2")
                }
                R.id.boton_tres->{
                    editNumeros.append("3")
                }
                R.id.boton_cuatro->{
                    editNumeros.append("4")
                }
            }*/
    }
    /*
    TextWatcher es una interfaz de Android que
    puedes implementar en una clase para recibir notificaciones cada vez que
    se produce un cambio en un objeto EditText.
     */
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        Log.v("textoBefore", p0.toString())
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        Log.v("textoOn", p0.toString())
    }

    override fun afterTextChanged(p0: Editable?) {
        Log.v("textoAfter", p0.toString())
    }

    // Logcat-> package:com.example.t3_estados_botones

}