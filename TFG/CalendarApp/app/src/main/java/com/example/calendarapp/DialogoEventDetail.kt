package com.example.calendarapp

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DialogoEventDetail : DialogFragment() {

    companion object {
        private const val ARG_DAY = "arg_day"
        private const val ARG_EVENTS = "arg_events"

        fun newInstance(day: Int, events: List<String>): DialogoEventDetail {
            val dialog = DialogoEventDetail()
            val args = Bundle().apply {
                putInt(ARG_DAY, day)
                putStringArrayList(ARG_EVENTS, ArrayList(events))
            }
            dialog.arguments = args
            return dialog
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val day = requireArguments().getInt(ARG_DAY)
        val events = requireArguments().getStringArrayList(ARG_EVENTS) ?: emptyList<String>()

        val eventText = buildEventText(events)

        return AlertDialog.Builder(requireContext())
            .setTitle("Detalles del Evento - Día $day")
            .setMessage(eventText)
            .setPositiveButton(R.string.dialog_event_detail_positive_button) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }

    private fun buildEventText(events: List<String>): String {
        return if (events.isNotEmpty()) {
            events.joinToString("\n")
        } else {
            "No hay eventos para este día"
        }
    }
}
