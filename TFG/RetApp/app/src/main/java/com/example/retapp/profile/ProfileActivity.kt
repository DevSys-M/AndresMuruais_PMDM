package com.example.retapp.profile

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.retapp.MainActivity
import com.example.retapp.R
import com.example.retapp.common.NodeNames
import com.example.retapp.login.LoginActivity
import com.example.retapp.password.ChangePasswordActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class ProfileActivity : AppCompatActivity() {

    private lateinit var etEmail: TextInputEditText
    private lateinit var etName: TextInputEditText

    private lateinit var firebaseUser: FirebaseUser
    private lateinit var databaseReference: DatabaseReference

    private lateinit var fileStorage: StorageReference
    private var localFileUri: Uri? = null
    private var serverFileUri: Uri? = null
    private lateinit var ivProfile: ImageView
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressBar: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        etEmail = findViewById(R.id.etEmail)
        etName = findViewById(R.id.etName)

        ivProfile = findViewById(R.id.ivProfile)

        fileStorage = FirebaseStorage.getInstance().getReference()

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUser = firebaseAuth.currentUser!!

        progressBar = findViewById(R.id.progressBar)

        if (firebaseUser!=null) {

            etName.setText(firebaseUser.displayName)
            etEmail.setText(firebaseUser.email)

            serverFileUri = firebaseUser.photoUrl as? Uri
                    // ?: Uri.parse("android.resource://${packageName}/${R.drawable.default_profile}")


            if (serverFileUri != null) {
                // implementacion Glide library
                Glide.with(this)
                    .load(serverFileUri)
                    .placeholder(R.drawable.default_profile)
                    .error(R.drawable.default_profile)
                    .into(ivProfile)
            }

        }

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

    fun btnLogoutClick(view: View) {

        var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
        finish()

    }

    fun btnSaveClick(view: View) {

        if (etName.text.toString().trim().equals("")) {
            etName.setError(getString(R.string.enter_name))
        }
        else {
            if (localFileUri != null){
                updateNameAndPhoto()
            }
            else {
                updateOnlyName()
            }
        }

    }


    fun changeImage(view: View) {

        if (serverFileUri == null) {

            pickImage()
        }
        else {
            // inflar Menu
           var popupMenu: PopupMenu = PopupMenu(this, view)
            popupMenu.menuInflater.inflate(R.menu.menu_picture, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                val id: Int = menuItem.itemId

                if (id== R.id.mnuChangePic) {
                    pickImage()
                }

               else if (id == R.id.mnuRemovePic) {

                    removePhoto()
                }

                false
            }

            popupMenu.show()

        }
    }

    private fun pickImage() {
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

    private fun removePhoto() {
        progressBar.visibility = View.VISIBLE
        var request: UserProfileChangeRequest = UserProfileChangeRequest.Builder()
            .setDisplayName(etName.text.toString().trim())
            .setPhotoUri(null)
            .build()

        firebaseUser.updateProfile(request).addOnCompleteListener {
            progressBar.visibility = View.GONE
            if (it.isSuccessful) {

                var userID: String = firebaseUser.uid
                databaseReference = FirebaseDatabase.getInstance().getReference().child(
                    NodeNames().USERS)

                // hashmap da valores a cada attr de clase Nodenames y se crean Nodos en Realtime Database
                val hashMap: HashMap<String, String> = HashMap()
                hashMap.put(NodeNames().PHOTO, "")

                // actualiza
                databaseReference.child(userID).setValue(hashMap)
                    .addOnCompleteListener {

                        Toast.makeText(this@ProfileActivity, getString(R.string.photo_removed_successfully), Toast.LENGTH_SHORT).show()



                    }

            }

            else {
                Toast.makeText(this@ProfileActivity, getString(R.string.failed_to_update_profile, it.exception), Toast.LENGTH_SHORT).show()
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
                            databaseReference = FirebaseDatabase.getInstance().getReference().child(
                                NodeNames().USERS)

                            // hashmap da valores a cada attr de clase Nodenames y se crean Nodos en Realtime Database
                            val hashMap: HashMap<String, String> = HashMap()
                            hashMap.put(NodeNames().NAME, etName.text.toString().trim())
                            serverFileUri?.path?.let { it1 -> hashMap.put(NodeNames().PHOTO, it1) }

                            // actualiza
                            databaseReference.child(userID).setValue(hashMap)
                                .addOnCompleteListener {

                                   finish()


                                }

                        }

                        else {
                            Toast.makeText(this@ProfileActivity, getString(R.string.failed_to_update_profile, it.exception), Toast.LENGTH_SHORT).show()
                        }
                    }


                }
            }
        }


    }



    // Método para si el usuario sólo actualiza nombre y no imagen de perfil
    private fun updateOnlyName() {
        progressBar.visibility = View.VISIBLE
        var request: UserProfileChangeRequest = UserProfileChangeRequest.Builder()
            .setDisplayName(etName.text.toString().trim())
            .build()
        firebaseUser.updateProfile(request).addOnCompleteListener {
            progressBar.visibility = View.GONE
            if (it.isSuccessful) {

                var userID: String = firebaseUser.uid
                databaseReference = FirebaseDatabase.getInstance().getReference().child(NodeNames().USERS)

                // hashmap da valores a cada attr de clase Nodenames y se crean Nodos en Realtime Database
                val hashMap: HashMap<String, String> = HashMap()
                hashMap.put(NodeNames().NAME, etName.text.toString().trim())
                // solo se cambia el nombre del usuario aqui

                // actualiza
                databaseReference.child(userID).setValue(hashMap).addOnCompleteListener {

                    finish()

                }

            }

            else {
                Toast.makeText(this@ProfileActivity, getString(R.string.failed_to_update_profile, it.exception), Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun btnChangePasswordClick(view: View) {
        startActivity(Intent(this@ProfileActivity, ChangePasswordActivity::class.java))
    }


}