import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.demoapp.R

class EventDetailDialog : DialogFragment() {

    companion object {
        private const val ARG_DAY = "arg_day"
        private const val ARG_EVENTS = "arg_events"

        fun newInstance(day: Int, events: List<String>): EventDetailDialog {
            val dialog = EventDetailDialog()
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
            .setTitle("Event Details - Day $day")
            .setMessage(eventText)
            .setPositiveButton(R.string.dialog_add_event_positive_button) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }

    private fun buildEventText(events: List<String>): String {
        return if (events.isNotEmpty()) {
            events.joinToString("\n")
        } else {
            "No events for this day"
        }
    }
}
