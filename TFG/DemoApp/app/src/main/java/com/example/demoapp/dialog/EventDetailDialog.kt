package com.example.demoapp.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class EventDetailDialog(private val events: List<String>) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Eventos del día")
            builder.setItems(events.toTypedArray(), null)

            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
    interface OnEventAddedListener {
        fun onEventAdded(eventText: String)
    }
}
