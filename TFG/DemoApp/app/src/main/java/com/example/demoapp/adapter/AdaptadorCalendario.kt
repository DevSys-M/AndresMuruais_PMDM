import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R

class AdaptadorCalendario(
    private val eventos: Map<Int, List<String>>,
    private val onDayClickListener: OnDayClickListener
) : RecyclerView.Adapter<AdaptadorCalendario.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calendario, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val day = position + 1
        val eventsForDay = eventos[day] ?: emptyList()

        holder.tvDay.text = day.toString()

        if (eventsForDay.isNotEmpty()) {
            holder.itemView.setBackgroundColor(Color.YELLOW)
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE)
        }

        holder.itemView.setOnClickListener {
            onDayClickListener.onDayClick(day, eventsForDay)
        }

        holder.itemView.setOnLongClickListener {
            onDayClickListener.onDayLongClick(day)
            true
        }
    }

    override fun getItemCount(): Int {
        return 31 // Ajusta este valor según el número de días del mes
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDay: TextView = itemView.findViewById(R.id.tvDia)
    }

    interface OnDayClickListener {
        fun onDayClick(day: Int, events: List<String>)
        fun onDayLongClick(day: Int)
    }
}
