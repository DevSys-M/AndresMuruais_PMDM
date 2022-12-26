
package com.example.t3_botones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.RadioButton
import com.example.t3_botones.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity(), View.OnClickListener,
    CompoundButton.OnCheckedChangeListener {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        acciones()
    }

    private fun acciones() {
        binding.botonPulsar.setOnClickListener(this)
        binding.grupoRadios.setOnCheckedChangeListener { radioGroup, i ->
            var radioSeleccionado: RadioButton = findViewById(binding.grupoRadios.checkedRadioButtonId);
            Snackbar.make(radioSeleccionado, radioSeleccionado.text, Snackbar.LENGTH_SHORT).show()
        }
        binding.toggleEstado.setOnCheckedChangeListener(this)

        binding.checkEstado.setOnCheckedChangeListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.boton_pulsar->{
                binding.toggleEstado.isChecked = !binding.toggleEstado.isChecked
                var radioSelecionado: RadioButton = findViewById(binding.grupoRadios.checkedRadioButtonId)
                when(radioSelecionado.id){
                    binding.radioUno.id ->{binding.checkEstado.isChecked = !binding.checkEstado.isChecked}
                    binding.radioDos.id ->{binding.toggleEstado.isChecked = !binding.toggleEstado.isChecked}
                    binding.radioTres.id->{Snackbar.make(p0,radioSelecionado.text,Snackbar.LENGTH_LONG).show()}
                }

            }
            R.id.toggle_estado -> {
                /*
                    Snackbar.make(p0, toggleEstado.isChecked.toString() ,
                        Snackbar.LENGTH_LONG).show()
                 */
            }
        }
    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        when(p0!!.id) {
            R.id.toggle_estado -> {
                Snackbar.make(p0, p1.toString(), Snackbar.LENGTH_SHORT).show()
            }
            R.id.check_estado -> {
                Snackbar.make(p0, p1.toString(), Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}