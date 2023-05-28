import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R

class FirstFragment : Fragment(), AdaptadorCalendario.OnDayClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterCalendario: AdaptadorCalendario

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        recyclerView = view.findViewById(R.id.recyclerCalendar)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 7)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val eventos = mapOf(1 to listOf("Evento 1"), 5 to listOf("Evento 2", "Evento 3"))
        adapterCalendario = AdaptadorCalendario(eventos, this)
        recyclerView.adapter = adapterCalendario
    }

    override fun onDayClick(day: Int, events: List<String>) {
        // Mostrar eventos para el día seleccionado
        val eventDetailDialog = EventDetailDialog.newInstance(day, events)
        eventDetailDialog.show(parentFragmentManager, "EventDetailDialog")
    }

    override fun onDayLongClick(day: Int) {
        // Mostrar diálogo para crear un evento en el día seleccionado
        val addEventDialog = AddEventDialog.newInstance(day)
        addEventDialog.show(parentFragmentManager, "AddEventDialog")
    }
}
