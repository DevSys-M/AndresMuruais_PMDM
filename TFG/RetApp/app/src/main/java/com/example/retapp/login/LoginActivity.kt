package com.example.retapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.retapp.MainActivity
import com.example.retapp.MessageActivity
import com.example.retapp.R
import com.example.retapp.signup.SignupActivity
import com.example.retapp.databinding.ActivityLoginBinding
import com.example.retapp.password.ResetPasswordActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.example.retapp.common.Util

class LoginActivity : AppCompatActivity() {


    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText

    private lateinit var email: String
    private lateinit var password: String

    private lateinit var progressBar: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)

        progressBar = findViewById(R.id.progressBar)

    }

    public fun tvSignupClick(v: View) {

        startActivity(Intent(this, SignupActivity::class.java))

    }
    public fun btnLogicClick(v: View) {

        email = etEmail.text.toString().trim()
        password = etPassword.text.toString().trim()

        if (email.equals("")) {
            etEmail.setError(getString(R.string.enter_email))

        } else if (password.equals("")) {

            etPassword.setError(getString(R.string.enter_password))


        } else {

            val util = Util()
            // Comprobar conexion con Firebase
            if (util.connectionAvailable(this)) {


                progressBar.visibility = View.VISIBLE

                var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
                // AuthResult
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        progressBar.visibility = View.GONE
                        if (it.isSuccessful) {

                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()


                        } else {

                            Toast.makeText(
                                this@LoginActivity,
                                "Logueo Fallido: " + it.exception,
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }
            }
            // si no hay conexión
            else {
                startActivity(Intent(this@LoginActivity , MessageActivity::class.java))
            }

        }
    }

    fun tvResetPasswordClick(view: View) {

        startActivity(Intent(this@LoginActivity, ResetPasswordActivity::class.java))
    }

    // Implementar login guardado. El usuario no vera la pantalla de loguin nunca más si está logueado
    override fun onStart() {
        super.onStart()

        var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        var firebaseUser: FirebaseUser? = firebaseAuth.currentUser

        if (firebaseUser!= null) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }

    }

}