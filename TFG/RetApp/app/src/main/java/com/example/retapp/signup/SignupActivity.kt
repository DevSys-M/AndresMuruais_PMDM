package com.example.retapp.signup

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Patterns
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.retapp.R
import com.example.retapp.common.NodeNames
import com.example.retapp.login.LoginActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.net.URI


class SignupActivity : AppCompatActivity() {

    private lateinit var etEmail: TextInputEditText
    private lateinit var etName: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var etConfirmPassword: TextInputEditText

    private lateinit var email: String
    private lateinit var name: String
    private lateinit var password: String
    private lateinit var confirmPassword: String

    private lateinit var firebaseUser: FirebaseUser
    private lateinit var databaseReference: DatabaseReference

    private lateinit var fileStorage: StorageReference
    private var localFileUri: Uri? = null
    private var serverFileUri: Uri? = null
    private lateinit var ivProfile: ImageView

    private lateinit var progressBar: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        etEmail = findViewById(R.id.etEmail)
        etName = findViewById(R.id.etName)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        ivProfile = findViewById(R.id.ivProfile)
        progressBar = findViewById(R.id.progressBar)

        fileStorage = FirebaseStorage.getInstance().getReference()


    }

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            if (data != null) {
                val selectedImageUri: Uri? = data.data
                if (selectedImageUri != null) {
                    contentResolver.takePersistableUriPermission(
                        selectedImageUri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )
                    localFileUri = selectedImageUri
                    ivProfile.setImageURI(localFileUri)
                }
            }
        }
    }

    fun pickImage(v: View) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        pickImageLauncher.launch(intent)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 102) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent: Intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, 101)
            } else {
                Toast.makeText(this, getString(R.string.permission_required), Toast.LENGTH_SHORT).show()
            }
        }
    }







    private fun updateNameAndPhoto() {

        var strFileName: String = firebaseUser.uid + ".jpg"
        val fileRef: StorageReference = fileStorage.child("images/" + strFileName)

        progressBar.visibility = View.VISIBLE

        fileRef.putFile(localFileUri!!).addOnCompleteListener { it ->
            progressBar.visibility = View.GONE
            if (it.isSuccessful) {
                fileRef.downloadUrl.addOnSuccessListener {
                    serverFileUri = it
                    var request: UserProfileChangeRequest = UserProfileChangeRequest.Builder()
                        .setDisplayName(etName.text.toString().trim())
                        .setPhotoUri(serverFileUri)
                        .build()

                    firebaseUser.updateProfile(request).addOnCompleteListener {
                        if (it.isSuccessful) {

                            var userID: String = firebaseUser.uid
                            databaseReference = FirebaseDatabase.getInstance().getReference().child(NodeNames().USERS)

                            // hashmap da valores a cada attr de clase Nodenames y se crean Nodos en Realtime Database
                            val hashMap: HashMap<String, String> = HashMap()
                            hashMap.put(NodeNames().NAME, etName.text.toString().trim())
                            hashMap.put(NodeNames().EMAIL, etEmail.text.toString().trim())
                            hashMap.put(NodeNames().ONLINE, "true")
                            serverFileUri?.path?.let { it1 -> hashMap.put(NodeNames().PHOTO, it1) }

                            // actualiza
                            databaseReference.child(userID).setValue(hashMap)
                                .addOnCompleteListener {

                                Toast.makeText(this@SignupActivity, getString(R.string.user_created_successfully), Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@SignupActivity, LoginActivity::class.java))


                            }

                        }

                        else {
                            Toast.makeText(this@SignupActivity, getString(R.string.failed_to_update_profile, it.exception), Toast.LENGTH_SHORT).show()
                        }
                    }


                }
            }
        }


    }



    // Método para si el usuario sólo actualiza nombre y no imagen de perfil
    private fun updateOnlyName() {

        progressBar.visibility = View.VISIBLE
        var request: UserProfileChangeRequest = UserProfileChangeRequest.Builder().setDisplayName(etName.text.toString().trim()).build()
        firebaseUser.updateProfile(request).addOnCompleteListener {
            progressBar.visibility = View.GONE
            if (it.isSuccessful) {

                var userID: String = firebaseUser.uid
                databaseReference = FirebaseDatabase.getInstance().getReference().child(NodeNames().USERS)

                // hashmap da valores a cada attr de clase Nodenames y se crean Nodos en Realtime Database
                val hashMap: HashMap<String, String> = HashMap()
                hashMap.put(NodeNames().NAME, etName.text.toString().trim())
                hashMap.put(NodeNames().EMAIL, etEmail.text.toString().trim())
                hashMap.put(NodeNames().ONLINE, "true")
                hashMap.put(NodeNames().PHOTO, "")

                progressBar.visibility = View.VISIBLE
                // actualiza datos con el Hash de la clase Nodenames
                databaseReference.child(userID).setValue(hashMap).addOnCompleteListener {

                    progressBar.visibility = View.GONE
                    Toast.makeText(this@SignupActivity, getString(R.string.user_created_successfully), Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SignupActivity, LoginActivity::class.java))


                }

            }

            else {
                Toast.makeText(this@SignupActivity, getString(R.string.failed_to_update_profile, it.exception), Toast.LENGTH_SHORT).show()
            }
        }
    }




    fun btnSignupClick(v: View)
    {
        email = etEmail.text.toString().trim()
        name = etName.text.toString().trim()
        password = etPassword.text.toString().trim()
        confirmPassword = etConfirmPassword.text.toString().trim()

        if (email.equals("")) {

            etEmail.setError(getString(R.string.enter_email))


        }
        else if (name.equals("")) {
            etName.setError(getString(R.string.enter_name))
        }
        else if (etPassword.equals("")) {
            etPassword.setError(getString(R.string.enter_password))
        }

        else if (confirmPassword.equals("")) {
            etConfirmPassword.setError(getString(R.string.confirm_password))
        }


        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError(getString(R.string.enter_correct_email))
        }

        else if (!password.equals(confirmPassword)) {

            etConfirmPassword.setError(getString(R.string.password_mismatch))
        }

        else {

            progressBar.visibility = View.VISIBLE
            val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

            firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    progressBar.visibility = View.GONE
                    if (it.isSuccessful) {

                        firebaseUser = firebaseAuth.currentUser!!
                        // comprobar si se actualizo imagen, ejecutar un metodo u otro
                        if (localFileUri!= null) {
                            updateNameAndPhoto()
                        }
                        else {
                            updateOnlyName()
                        }
                    }

                    else {
                        Toast.makeText(this@SignupActivity, getString(R.string.signup_failed, it.exception), Toast.LENGTH_SHORT).show()


                    }

                }
        }


    }


}