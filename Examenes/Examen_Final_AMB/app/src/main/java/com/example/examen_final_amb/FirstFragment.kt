package com.example.examen_final_amb

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.examen_final_amb.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    private var  contador: Int = 0;

    override fun onAttach(context: Context) {
        super.onAttach(context)
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogin.setOnClickListener {
            if(binding.editMail.text.isNotEmpty() && binding.editPass.text.isNotEmpty()){
               auth.signInWithEmailAndPassword(binding.editMail.text.toString(),binding.editPass.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        contador++;
                        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                    }

               }



            }else {
                Snackbar.make(binding.buttonLogin,"Usuario o contraseña incorrectos",Snackbar.LENGTH_SHORT).show()
            }


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}