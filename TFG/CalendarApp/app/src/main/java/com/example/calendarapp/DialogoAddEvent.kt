package com.example.calendarapp

import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DialogoAddEvent : DialogFragment() {

    companion object {
        private const val ARG_DAY = "arg_day"

        fun newInstance(day: Int): DialogoAddEvent {
            val dialog = DialogoAddEvent()
            val args = Bundle().apply {
                putInt(ARG_DAY, day)
            }
            dialog.arguments = args
            return dialog
        }
    }

    private lateinit var etEventName: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val day = requireArguments().getInt(ARG_DAY)

        val rootView = requireActivity().layoutInflater.inflate(R.layout.dialog_add_event, null)
        etEventName = rootView.findViewById(R.id.etEventName)

        return AlertDialog.Builder(requireContext())
            .setTitle("Agregar Evento - Día $day")
            .setView(rootView)
            .setPositiveButton(R.string.dialog_add_event_positive_button) { dialog, _ ->
                val eventName = etEventName.text.toString()
                // Aquí puedes realizar alguna acción con el nombre del evento ingresado
                dialog.dismiss()
            }
            .setNegativeButton(R.string.dialog_add_event_negative_button) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }
}
