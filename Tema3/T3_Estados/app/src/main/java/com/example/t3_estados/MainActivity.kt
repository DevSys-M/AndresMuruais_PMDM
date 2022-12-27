package com.example.t3_estados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.t3_estados.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding

    var contador: Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //recuperar datos para landscape
        contador=savedInstanceState?.getInt("contador")?:0

    }
    override fun onStart() {
        super.onStart()
        binding.textoContador.text=contador.toString()
        acciones();
    }


    private fun acciones() {
        binding.botonIncrementar.setOnClickListener (this)
        binding.botonDecrementar.setOnClickListener (this)
        binding.botonReset.setOnClickListener (this)
    }
    /*
    Called to retrieve per-instance state from an activity before being killed so that the state can be restored in onCreate(Bundle)
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("contador",contador)
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.boton_incrementar->{
                contador++
            }
            R.id.boton_decrementar->{
                contador--
            }
            R.id.boton_reset->{
                contador=0
            }
        }
        binding.textoContador.text = contador.toString();
    }

}