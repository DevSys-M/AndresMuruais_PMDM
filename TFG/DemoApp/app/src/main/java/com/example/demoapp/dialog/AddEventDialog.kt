import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.demoapp.R

class AddEventDialog : DialogFragment() {

    companion object {
        private const val ARG_DAY = "arg_day"

        fun newInstance(day: Int): AddEventDialog {
            val dialog = AddEventDialog()
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
        etEventName = rootView.findViewById(R.id.editTextEvent)

        return AlertDialog.Builder(requireContext())
            .setTitle("Add Event - Day $day")
            .setView(rootView)
            .setPositiveButton(R.string.dialog_add_event_title) { dialog, _ ->
                val eventName = etEventName.text.toString()
                // Aquí puedes guardar el evento en la fecha seleccionada
                // Puedes usar eventName y day para guardar el evento en tu base de datos o en otra estructura de datos
                dialog.dismiss()
            }
            .setNegativeButton(R.string.dialog_add_event_negative_button) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }
}
