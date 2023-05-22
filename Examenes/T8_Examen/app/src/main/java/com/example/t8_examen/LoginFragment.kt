package com.example.t8_examen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.t8_examen.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    private var contador: Int =1;

    override fun onAttach(context: Context) {
        super.onAttach(context)
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.botonLogin.setOnClickListener {
            if (binding.editNombre.text.isNotEmpty() && binding.editPass.text.isNotEmpty()){
                val email = binding.editNombre.text.toString()
                val pass = binding.editPass.text.toString()
                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if(it.isSuccessful){
                        val bundle = Bundle()
                        bundle.putString("uid",auth.currentUser!!.uid)
                        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
                    } else{
                        Snackbar.make(binding.botonLogin,"Login error",Snackbar.LENGTH_SHORT).show()
                    }
                }
            }  else {
            Snackbar.make(binding.botonLogin, "Faltan datos",Snackbar.LENGTH_SHORT).show()
        }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}