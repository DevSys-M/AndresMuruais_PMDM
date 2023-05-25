package com.example.demoapp.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.demoapp.R
import com.example.demoapp.databinding.DialogAddEventBinding

class AddEventDialog : DialogFragment() {

    private var _binding: DialogAddEventBinding? = null
    private val binding get() = _binding!!

    private lateinit var onEventAddedListener: OnEventAddedListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_add_event, null)

        builder.setView(view)
            .setTitle(R.string.dialog_add_event_title)
            .setPositiveButton(R.string.dialog_add_event_positive_button) { dialog, _ ->
                val eventText = view.findViewById<EditText>(R.id.editTextEvent).text.toString()
                // Aquí puedes realizar la lógica para guardar el evento en la fecha seleccionada
                // y actualizar el adaptador del calendario si es necesario
                dialog.dismiss()
            }
            .setNegativeButton(R.string.dialog_add_event_negative_button) { dialog, _ ->
                dialog.cancel()
            }

        return builder.create()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setOnEventAddedListener(listener: OnEventAddedListener) {
        onEventAddedListener = listener
    }

    interface OnEventAddedListener {
        fun onEventAdded(date: String, eventText: String)
    }

    companion object {
        private const val ARG_DATE = "date"

        fun newInstance(date: String): AddEventDialog {
            val dialog = AddEventDialog()
            val args = Bundle()
            args.putString(ARG_DATE, date)
            dialog.arguments = args
            return dialog
        }
    }
}
