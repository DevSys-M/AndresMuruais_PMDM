package com.example.demoapp.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.demoapp.R

class AddEventDialog : DialogFragment() {

    private var onEventAddedListener: OnEventAddedListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(requireContext())
        val view = inflater.inflate(R.layout.dialog_add_event, null)

        val editTextEvent = view.findViewById<EditText>(R.id.editTextEvent)
        val buttonAdd = view.findViewById<Button>(R.id.buttonAdd)
        val buttonCancel = view.findViewById<Button>(R.id.buttonCancel)

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(R.string.dialog_title_add_event)
            .setView(view)
            .create()

        buttonAdd.setOnClickListener {
            val eventText = editTextEvent.text.toString().trim()
            if (eventText.isNotEmpty()) {
                onEventAddedListener?.onEventAdded(eventText)
                dismiss()
            }
        }

        buttonCancel.setOnClickListener {
            dismiss()
        }

        dialog.setOnShowListener {
            editTextEvent.requestFocus()
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(editTextEvent, InputMethodManager.SHOW_IMPLICIT)
        }

        return dialog
    }

    interface OnEventAddedListener {
        fun onEventAdded(eventText: String)
    }

    fun setOnEventAddedListener(listener: OnEventAddedListener) {
        onEventAddedListener = listener
    }

    companion object {
        fun newInstance(): AddEventDialog {
            return AddEventDialog()
        }
    }
}
