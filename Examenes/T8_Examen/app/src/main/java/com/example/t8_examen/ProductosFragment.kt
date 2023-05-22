package com.example.t8_examen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.disklrucache.DiskLruCache.Value
import com.example.t8_examen.adapter.AdaptadorProducto
import com.example.t8_examen.databinding.FragmentProductosBinding
import com.example.t8_examen.model.Producto
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ProductosFragment : Fragment() {

    private var _binding: FragmentProductosBinding? = null

    private lateinit var dataBase: FirebaseDatabase

    private lateinit var uid: String
    private var contador: Int = 1

    private lateinit var adaptadorProducto: AdaptadorProducto

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adaptadorProducto = AdaptadorProducto(requireContext())

        //bundle
        uid = arguments?.getString("uid") ?: "defecto"
        //Firebase
        dataBase = FirebaseDatabase.getInstance("https://pdpm-project-default-rtdb.europe-west1.firebasedatabase.app/")

    }
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProductosBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val referencia = dataBase.getReference("usuarios").child(uid!!)
        //TODO obtener la referencia para sumar uno a logonCount
        referencia.child("logoncount").setValue(contador)

        val referenciaProductos = dataBase.getReference("productos")

        referenciaProductos.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                adaptadorProducto.vaciarLista()
                for (item in snapshot.children){

                    val producto = item.getValue(Producto::class.java)
                    adaptadorProducto.agregarProducto(producto!!)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        binding.recyclerSitio.adapter = adaptadorProducto
        binding.recyclerSitio.layoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}