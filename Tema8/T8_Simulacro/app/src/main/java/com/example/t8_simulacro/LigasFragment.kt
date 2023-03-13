package com.example.t8_simulacro

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t8_simulacro.adapters.AdaptadorLigas
import com.example.t8_simulacro.databinding.FragmentLigasBinding
import com.example.t8_simulacro.models.Ligas
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LigasFragment : Fragment() {

    private var _binding: FragmentLigasBinding? = null
    private lateinit var database: FirebaseDatabase
    private lateinit var adaptadorLigas: AdaptadorLigas

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        database = FirebaseDatabase.getInstance("https://pdpm-project-default-rtdb.europe-west1.firebasedatabase.app/")
        adaptadorLigas = AdaptadorLigas(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLigasBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onStart() {
        super.onStart()

        val referencia = database.getReference("ligas")

        referencia.child("ligues").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.v("base_datos",snapshot.key.toString())
                for(item in snapshot.children){
                    Log.v("base_datos",item.key.toString())
                    val liga = item.getValue(Ligas::class.java)
                    adaptadorLigas.agregarLiga(liga!!)
                    Log.v("base_datos","Producto ${liga!!.nombre} ${liga!!.favoritas}")
                }
                binding.recyclerLigas.adapter =adaptadorLigas
                binding.recyclerLigas.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}