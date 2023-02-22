package com.example.t8_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.t8_firebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        auth = Firebase.auth
        setContentView(binding.root)
        // crear usuario con email /pass
        /*
        auth.createUserWithEmailAndPassword("amuru@gmail.com","Retammar1a")
            .addOnCompleteListener {
                if(it.isSuccessful){
                    Log.v("prueba_fb","Creacion de usuario correcta")
                }else{
                    Log.v("prueba_fb","Creacion de usuario incorrecta")
                }
            }
         */
        auth.signInWithEmailAndPassword("amuru@gmail.com","Retammar1a").addOnCompleteListener {
            if(it.isSuccessful){
                Log.v("prueba_fb","Login  correcto con ")
                //sacar los datos a la siguiente pantalla
                val usuarioLogueado=auth.currentUser
                usuarioLogueado?.uid
            }else{
                Log.v("prueba_fb","Login  incorrecto")
            }
        }
    }
}