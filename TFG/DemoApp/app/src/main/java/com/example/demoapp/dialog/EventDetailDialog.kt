package com.example.demoapp.dialog

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.demoapp.R

class EventDetailDialog : DialogFragment() {

    private var events: List<String>? = null
    private var onEventDetailDismissedListener: OnEventDetailDismissedListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_event_detail, null)

        val textViewEvents = view.findViewById<TextView>(R.id.textViewEvents)
        val buttonOk = view.findViewById<Button>(R.id.buttonOk)

        events?.let {
            textViewEvents.text = it.joinToString("\n")
        }

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(R.string.dialog_title_event_detail)
            .setView(view)
            .create()

        buttonOk.setOnClickListener {
            dismiss()
        }

        dialog.setOnDismissListener {
            onEventDetailDismissedListener?.onEventDetailDismissed()
        }

        return dialog
    }

    interface OnEventDetailDismissedListener {
        fun onEventDetailDismissed()
    }

    fun setOnEventDetailDismissedListener(listener: OnEventDetailDismissedListener) {
        onEventDetailDismissedListener = listener
    }

    companion object {
        fun newInstance(events: List<String>): EventDetailDialog {
            val dialog = EventDetailDialog()
            dialog.events = events
            return dialog
        }
    }
}
