package com.example.retapp.password

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.retapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var etEmail: TextInputEditText
    private lateinit var tvMessage: TextView
    private lateinit var llResetPassword: LinearLayout
    private lateinit var llMessage: LinearLayout

    private lateinit var btnRetry: Button
    private lateinit var progressBar: View



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        etEmail = findViewById(R.id.etEmail)
        tvMessage = findViewById(R.id.tvMessage)
        llMessage = findViewById(R.id.llMessage)
        llResetPassword = findViewById(R.id.llResetPassword)

        btnRetry = findViewById(R.id.btnRetry)
        progressBar = findViewById(R.id.progressBar)

    }

    fun btnResetPasswordClick(view: View) {

        var email : String = etEmail.text.toString().trim()

        if (email.equals("")) {
            etEmail.setError(getString(R.string.enter_email))
        }
        else {

            var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
            progressBar.visibility = View.VISIBLE

            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {

                progressBar.visibility = View.GONE
                llResetPassword.visibility = View.GONE
                llMessage.visibility = View.VISIBLE

                if (it.isSuccessful) {
                    tvMessage.setText(getString(R.string.reset_password_instructions, email))

                    object : CountDownTimer(60000, 1000) {
                        override fun onTick(millisUntilFinished: Long) {

                           btnRetry.text = getString(R.string.resend_timer, (millisUntilFinished / 1000).toString())
                            // el boton no hace nada
                            btnRetry.setOnClickListener(null)

                        }

                        override fun onFinish() {
                            // Cuando se acaba el timer
                            btnRetry.setText(R.string.retry)
                            btnRetry.setOnClickListener {
                                llResetPassword.visibility = View.VISIBLE
                                llMessage.visibility = View.GONE
                            }


                        }
                    }.start()


                }
                else {
                    tvMessage.text = getString(R.string.email_sent_failed, it.exception.toString())
                    // Cuando se acaba el timer
                    btnRetry.setText(R.string.retry)
                    btnRetry.setOnClickListener {
                        llResetPassword.visibility = View.VISIBLE
                        llMessage.visibility = View.GONE
                    }
                }

            }

        }

    }


    fun btnCloseClick(view: View) {

        finish()
    }
}