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
import com.example.t4_conversor.model.Moneda
import com.google.android.material.snackbar.Snackbar

class DialogoPersonalizado : DialogFragment() {

    private lateinit var vista: View;
    private lateinit var spinnerOrigen: Spinner
    private lateinit var adaptadorOrigen: ArrayAdapter<CharSequence>
    private lateinit var spinnerDestino: Spinner
    private lateinit var adaptadorDestino: ArrayAdapter<CharSequence>
    private lateinit var editDinero: EditText
    private lateinit var botonCambio: Button

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
                listener.onDialogoSelected(Moneda(spinnerOrigen.selectedItem.toString(),spinnerDestino.selectedItem.toString() ,
                    editDinero.text.toString()))
                dismiss()
            } else {
                Snackbar.make(vista, "Falta algun dato", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    interface onDialogoListener{
       fun onDialogoSelected(moneda: Moneda)
    }
}