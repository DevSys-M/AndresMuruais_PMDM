package com.example.t8_simulacro

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.t8_simulacro.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onAttach(context: Context) {
        super.onAttach(context)
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.botonRegistro.setOnClickListener {
            val nombre = binding.editNombre.text.toString()
            val edad = binding.editEdad.text.toString().toInt()
            val pass = binding.editPass.text.toString()
            auth.signInWithEmailAndPassword(nombre,pass).addOnCompleteListener {
                if(it.isSuccessful){
                    val bundle= Bundle()
                    bundle.putString("nombre",nombre)
                    bundle.putInt("edad",edad)
                    bundle.putString("uid",auth.currentUser!!.uid)
                    findNavController().navigate(R.id.action_SecondFragment_to_secondActivity)
                }else{
                    Snackbar.make(binding.botonRegistro,"Fallo al registrar",Snackbar.LENGTH_SHORT).show()
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}