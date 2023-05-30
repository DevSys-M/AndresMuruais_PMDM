package com.example.calendarapp

import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DialogoAddEvent : DialogFragment() {

    private var onAddEventListener: ((event: String) -> Unit)? = null

    private lateinit var etEventName: EditText

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


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val day = requireArguments().getInt(ARG_DAY)

        val rootView = requireActivity().layoutInflater.inflate(R.layout.dialog_add_event, null)
        etEventName = rootView.findViewById(R.id.etEventName)

        return AlertDialog.Builder(requireContext())
            .setTitle("Agregar Evento - Día $day")
            .setView(rootView)
            .setPositiveButton(R.string.dialog_add_event_positive_button) { dialog, _ ->
                val eventName = etEventName.text.toString()
                addEvent(eventName) // Llamar al método addEvent y pasar el nombre del evento
                dialog.dismiss()
            }
            .setNegativeButton(R.string.dialog_add_event_negative_button) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }

    private fun addEvent(event: String) {
            onAddEventListener?.invoke(event)
            dismiss()
    }

    fun setOnAddEventListener(listener: (event: String) -> Unit) {
        onAddEventListener = listener
    }

}
