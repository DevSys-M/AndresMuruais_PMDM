package com.example.t4_conversor.dialog


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.t4_conversor.R
import com.google.android.material.snackbar.Snackbar

class DialogoPersonalizado : DialogFragment(), AdapterView.OnItemSelectedListener {

    private lateinit var vista: View;
    private lateinit var spinnerOrigen: Spinner
    private lateinit var adaptadorOrigen: ArrayAdapter<CharSequence>
    private lateinit var spinnerDestino: Spinner
    private lateinit var adaptadorDestino: ArrayAdapter<CharSequence>
    private lateinit var editDinero: EditText
    private lateinit var botonCambio: Button
    private lateinit var origen: String
    private lateinit var destino: String
    private lateinit var listener: onDialogoListener


    override fun onAttach(context: Context) {
        super.onAttach(context)
        vista = LayoutInflater.from(context).inflate(R.layout.item_dialog, null);

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder = AlertDialog.Builder(requireContext())
        builder.setView(vista)
        this.listener = context as onDialogoListener
        return builder.create()
    }

    override fun onStart() {
        super.onStart()
        spinnerOrigen = vista.findViewById(R.id.spinner_origen)
        spinnerDestino = vista.findViewById(R.id.spinner_destino)
        editDinero = vista.findViewById(R.id.edit_dinero)
        botonCambio = vista.findViewById(R.id.boton_cambio)

        adaptadorOrigen = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.array_monedas,
            android.R.layout.simple_spinner_item
        )
        adaptadorOrigen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOrigen.adapter = adaptadorOrigen
        adaptadorDestino = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.array_monedas,
            android.R.layout.simple_spinner_item
        )
        adaptadorOrigen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDestino.adapter = adaptadorDestino

    }

    override fun onResume() {
        super.onResume()
        botonCambio.setOnClickListener {
            if (editDinero.text.isNotEmpty()) {
                spinnerOrigen.onItemSelectedListener = this
                spinnerDestino.onItemSelectedListener = this
                listener.onDialogoTextoSelected(editDinero.text.toString());
                dismiss()
            } else {
                Snackbar.make(vista, "Falta algun dato", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when (p0!!.id) {
            spinnerOrigen.id -> {
                origen = adaptadorOrigen.getItem(p2).toString()
                listener.onDialogoOrigenSelected(origen)
            }
            spinnerDestino.id -> {
                destino = adaptadorDestino.getItem(p2).toString()
                listener.onDialogoDestinoSelected(destino)
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    interface onDialogoListener{
        fun onDialogoOrigenSelected(mensaje: String)
        fun onDialogoDestinoSelected(mensaje: String)
        fun onDialogoTextoSelected(mensaje: String)
    }
}