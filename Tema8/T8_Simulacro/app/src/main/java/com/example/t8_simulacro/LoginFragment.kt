package com.example.t8_simulacro

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.t8_simulacro.databinding.FragmentLoginBinding
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
            if(binding.editNombre.text.isNotEmpty() && binding.editPass.text.isNotEmpty()){
                // chequear login
                val nombre = binding.editNombre.text.toString()
                val pass = binding.editPass.text.toString()
                /* addOnCompleteListener es un método utilizado en la plataforma de desarrollo de Android que permite agregar un listener para ser notificado cuando una tarea asíncrona se complete.
                Se utiliza con frecuencia en aplicaciones que utilizan Firebase para realizar tareas asíncronas, como la autenticación de usuarios o el almacenamiento en la nube.
                Cuando se recibe la respuesta del servidor, la aplicación puede actuar en consecuencia.
                Esto permite que la aplicación continúe funcionando mientras la tarea se está ejecutando en segundo plano */
                auth.signInWithEmailAndPassword(nombre,pass).addOnCompleteListener {
                    /* la función isSuccessful se usa para verificar si una tarea se ha completado con éxito, la función isCancelled se usa para verificar si la tarea se ha cancelado y la función isComplete se usa para verificar si la tarea se ha completado, independientemente de su estado (éxito o cancelación).*/
                    if (it.isSuccessful){
                        val bundle = Bundle();
                        bundle.putString("nombre",nombre)
                        bundle.putString("uid",auth.currentUser!!.uid)
                        bundle.putInt("edad",-1)
                        findNavController().navigate(R.id.action_FirstFragment_to_secondActivity)
                    }else{
                        Snackbar.make(binding.botonLogin,"Datos erroneos",Snackbar.LENGTH_SHORT).show()
                    }
                }
            }

        }
        binding.botonRegister.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}