package com.example.retapp.password

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.retapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var etPassword: TextInputEditText
    private lateinit var etConfirmPassword: TextInputEditText
    private lateinit var progressBar: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        progressBar = findViewById(R.id.progressBar)

    }

    fun btnChangePasswordClick(view: View) {

        var password: String = etPassword.text.toString().trim()
        var confirmPassword: String = etConfirmPassword.text.toString().trim()

        if (password.equals("")) {

            etPassword.error = getString(R.string.enter_password)

        }

        else if (confirmPassword.equals("")) {

            etConfirmPassword.error = getString(R.string.confirm_password)
        }

        else if (!password.equals(confirmPassword)) {

            etConfirmPassword.error = getString(R.string.password_mismatch)

        }

        else {
            progressBar.visibility = View.VISIBLE
            var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
            var firebaseUser: FirebaseUser = firebaseAuth.currentUser!!

            if (firebaseUser!= null) {

                firebaseUser.updatePassword(password).addOnCompleteListener {
                    progressBar.visibility = View.GONE
                    if (it.isSuccessful) {
                        Toast.makeText(this@ChangePasswordActivity, R.string.password_changed_succesfully, Toast.LENGTH_SHORT).show()
                        finish()
                    }

                    else {
                        Toast.makeText(this@ChangePasswordActivity, getString(R.string.something_went_wrong, it.exception), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}