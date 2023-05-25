package com.example.demoapp.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.demoapp.databinding.DialogAddEventBinding

class AddEventDialog : DialogFragment() {

    private var _binding: DialogAddEventBinding? = null
    private val binding get() = _binding!!

    private lateinit var onEventAddedListener: OnEventAddedListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogAddEventBinding.inflate(LayoutInflater.from(context))

        val date = arguments?.getString(ARG_DATE)

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Agregar evento")
            .setView(binding.root)
            .setPositiveButton("Agregar") { _, _ ->
                val eventText = binding.editTextEvent.text.toString()
                if (eventText.isNotEmpty() && date != null) {
                    onEventAddedListener.onEventAdded(date, eventText)
                }
            }
            .setNegativeButton("Cancelar", null)
            .create()

        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)

        return dialog
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
