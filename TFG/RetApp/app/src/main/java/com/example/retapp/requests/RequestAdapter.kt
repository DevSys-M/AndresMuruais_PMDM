package com.example.retapp.requests


import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retapp.R
import com.example.retapp.common.Constants
import com.example.retapp.common.NodeNames
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class RequestAdapter(private val context: Context, private var requestModelList: MutableList<RequestModel>) :
    RecyclerView.Adapter<RequestAdapter.RequestViewHolder>() {

    private lateinit var databaseReferenceFriendRequests: DatabaseReference
    private lateinit var databaseReferenceChats: DatabaseReference
    private lateinit var currentUser: FirebaseUser




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.friend_request_layout, parent, false)
        return RequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        var requestModel = requestModelList[position]

        val constants: Constants = Constants()
        holder.tvFullName.text = requestModel.getUserName()
        val storage: FirebaseStorage = FirebaseStorage.getInstance()
        val storageRef: StorageReference = storage.getReferenceFromUrl("gs://retapp-43e84.appspot.com")
        val photoName: String = requestModel.getPhotoName()

        if (photoName.isNotEmpty()) {
            val imagesRef: StorageReference = storageRef.child(photoName)

            // sacar la Url
            imagesRef.downloadUrl.addOnSuccessListener { uri: Uri? ->
                Glide.with(context)
                    .load(uri)
                    .placeholder(R.drawable.default_profile)
                    .error(R.drawable.default_profile)
                    .into(holder.ivProfile)
            }.addOnFailureListener { exception: Exception ->
                // Si ocurre un error, cargar la imagen por defecto
                Glide.with(context)
                    .load(R.drawable.default_profile)
                    .placeholder(R.drawable.default_profile)
                    .error(R.drawable.default_profile)
                    .into(holder.ivProfile)
            }
        } else {
            // If photoName is empty, load the default image
            Glide.with(context)
                .load(R.drawable.default_profile)
                .placeholder(R.drawable.default_profile)
                .error(R.drawable.default_profile)
                .into(holder.ivProfile)
        }
        var nodes: NodeNames = NodeNames()
        // acceder al nodo de friend requests
        databaseReferenceFriendRequests = FirebaseDatabase.getInstance().reference.child(nodes.FRIEND_REQUESTS)
        databaseReferenceChats = FirebaseDatabase.getInstance().reference.child(nodes.CHATS)
        currentUser = FirebaseAuth.getInstance().currentUser!!


        // aceptar peticion de amistad
            // actualizar el estado del nodo como status accepted
            // crear nuevo nodo chats
        holder.btnAcceptRequest.setOnClickListener {

            holder.pbDecision.visibility = View.VISIBLE
            holder.btnDenyRequest.visibility = View.GONE
            holder.btnAcceptRequest.visibility = View.GONE

            val userId: String = requestModel.getUserId()
        //  crear nodos  para ambos usuarios, el que envia / recibe   + time del servidor
            databaseReferenceChats.child(currentUser.uid).child(userId)
                .child(nodes.TIME_STAMP).setValue(ServerValue.TIMESTAMP).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // usuario 1
                        databaseReferenceChats.child(userId).child(currentUser.uid)
                            .child(nodes.TIME_STAMP).setValue(ServerValue.TIMESTAMP).addOnCompleteListener { it1 ->
                                if (it1.isSuccessful) {
                                // usuario 2
                                databaseReferenceFriendRequests.child(currentUser.uid).child(userId)
                                    .child(nodes.REQUEST_TYPE).setValue(constants.REQUEST_STATUS_ACCEPTED).addOnCompleteListener {

                                        if (it.isSuccessful) {
                                            databaseReferenceFriendRequests.child(userId).child(currentUser.uid)
                                                .child(nodes.REQUEST_TYPE).setValue(constants.REQUEST_STATUS_ACCEPTED).addOnCompleteListener { it0 ->
                                                    if (it0.isSuccessful) {
                                                        // elimina el registro del nodo de friend requests

                                                        holder.pbDecision.visibility = View.GONE
                                                        holder.btnDenyRequest.visibility = View.VISIBLE
                                                        holder.btnAcceptRequest.visibility = View.VISIBLE

                                                    }
                                                    else {
                                                        handleException(holder, it0.exception)

                                                    }
                                                }
                                        }
                                        else {

                                            handleException(holder, it.exception)
                                        }

                                    }
                            }
                            else {
                                handleException(holder, it1.exception)
                            }
                        }

                    }
                    else {

                    handleException(holder, task.exception)

                    }
                }

        }

        holder.btnDenyRequest.setOnClickListener {
            // Eliminar el registro en nodos de request received y sent
                holder.pbDecision.visibility = View.VISIBLE
            holder.btnDenyRequest.visibility = View.GONE
            holder.btnAcceptRequest.visibility = View.GONE

            val userId: String = requestModel.getUserId()
            // eliminar el nodo con request type "received"
            databaseReferenceFriendRequests.child(currentUser.uid).child(userId).child(nodes.REQUEST_TYPE).setValue(null).addOnCompleteListener { it1 ->
                if (it1.isSuccessful) {
                    // eliminar el nodo con request type "sent"
                    databaseReferenceFriendRequests.child(userId).child(currentUser.uid).child(nodes.REQUEST_TYPE).setValue(null)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(context, context.getString(R.string.request_denied_request), Toast.LENGTH_SHORT).show()
                                holder.pbDecision.visibility = View.GONE
                                holder.btnDenyRequest.visibility = View.VISIBLE
                                holder.btnAcceptRequest.visibility = View.VISIBLE
                            }
                            else {
                                Toast.makeText(context, context.getString(R.string.failed_to_deny_request, it.exception), Toast.LENGTH_SHORT).show()
                                holder.pbDecision.visibility = View.GONE
                                holder.btnDenyRequest.visibility = View.VISIBLE
                                holder.btnAcceptRequest.visibility = View.VISIBLE
                            }
                        }
                }
                else {
                    Toast.makeText(context, context.getString(R.string.failed_to_deny_request, it1.exception), Toast.LENGTH_SHORT).show()
                    holder.pbDecision.visibility = View.GONE
                    holder.btnDenyRequest.visibility = View.VISIBLE
                    holder.btnAcceptRequest.visibility = View.VISIBLE
                }
            }




        }



    }

    private fun handleException(holder: RequestViewHolder ,  exception: Exception?) {
        Toast.makeText(context, context.getString(R.string.failed_to_accept_request, exception), Toast.LENGTH_SHORT).show()
        holder.pbDecision.visibility = View.GONE
        holder.btnDenyRequest.visibility = View.VISIBLE
        holder.btnAcceptRequest.visibility = View.VISIBLE
    }

    override fun getItemCount(): Int {
        return requestModelList.size
    }

    inner class RequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFullName: TextView = itemView.findViewById(R.id.tvFullName)
        val ivProfile: ImageView = itemView.findViewById(R.id.ivProfile)
        val btnAcceptRequest: Button = itemView.findViewById(R.id.btnAcceptRequest)
        val btnDenyRequest: Button = itemView.findViewById(R.id.btnDenyRequest)
        val pbDecision: ProgressBar = itemView.findViewById(R.id.pbDecision)
    }
}