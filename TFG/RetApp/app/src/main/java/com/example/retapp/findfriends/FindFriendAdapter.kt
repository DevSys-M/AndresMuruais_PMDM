package com.example.retapp.findfriends

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retapp.R
import com.example.retapp.common.Constants
import com.example.retapp.common.NodeNames
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import org.w3c.dom.Node

class FindFriendAdapter(private var context: Context,
                        private var findFriendModelList: MutableList<FindFriendModel>) : RecyclerView.Adapter<FindFriendAdapter.FindFriendViewHolder>() {

    // Enviar solicitud al pulsar botón. Declarar
    private lateinit var friendRequestDatabase: DatabaseReference
    private lateinit var currentUser: FirebaseUser
    private lateinit var userId: String

    // Listo, ahora en el FindFriendFragment, hacemos fetch de la Lista para ponerlo en el RecyclerView

     inner class FindFriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // elementos del custom layout
         var ivProfile: ImageView = itemView.findViewById(R.id.ivProfile)
         var tvFullName: TextView = itemView.findViewById(R.id.tvFullName)
         var btnSendRequest: Button = itemView.findViewById(R.id.btnSendRequest)
         var btnCancelRequest: Button = itemView.findViewById(R.id.btnCancelRequest)
         var pbRequest: View = itemView.findViewById(R.id.progressBar)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FindFriendViewHolder {
        // inflar el layout
        var view: View = LayoutInflater.from(context).inflate(R.layout.find_friends_layout, parent, false)
        return FindFriendViewHolder(view)
    }

    override fun getItemCount(): Int {
        return findFriendModelList.size
    }

    override fun onBindViewHolder(holder: FindFriendViewHolder, position: Int) {
        val friendModel: FindFriendModel = findFriendModelList[position]

        holder.tvFullName.text = friendModel.getUserName()
        // acceder a la foto de perfil

        var constants: Constants = Constants()
        var storage: FirebaseStorage = FirebaseStorage.getInstance()
        var storageRef: StorageReference = storage.getReferenceFromUrl("gs://retapp-43e84.appspot.com")

        var imagesRef: StorageReference = storageRef.child(friendModel.getPhotoName())

        // sacar la Url
        imagesRef.downloadUrl.addOnSuccessListener {
            Glide.with(context)
                .load(it)
                .placeholder(R.drawable.default_profile)
                .error(R.drawable.default_profile)
                .into(holder.ivProfile)
        }

        // Inicializar y crear nuevo nodo que guarde peticiones
        var NodePeticiones: NodeNames = NodeNames()
        friendRequestDatabase = FirebaseDatabase.getInstance().reference.child(NodePeticiones.FRIEND_REQUESTS)
        currentUser = FirebaseAuth.getInstance().currentUser!!

        // Comprobar el estado de la petición de amistad en el adapter para cómo mostrar los botones de petición
        if (friendModel.isRequestSent()) {
            // si enviamos true al fragment...
            holder.btnSendRequest.visibility = View.GONE
            holder.btnCancelRequest.visibility = View.VISIBLE
        } else {
            holder.btnSendRequest.visibility = View.VISIBLE
            holder.btnCancelRequest.visibility = View.GONE
        }



        holder.btnSendRequest.setOnClickListener {
            holder.btnSendRequest.visibility = View.GONE
            holder.pbRequest.visibility = View.VISIBLE

            userId = friendModel.getUserId()
            // Crear petición enviada
            friendRequestDatabase.child(currentUser.uid).child(userId).child(NodePeticiones.REQUEST_TYPE)
                .setValue(constants.REQUEST_STATUS_SENT).addOnCompleteListener { it ->
                    if (it.isSuccessful) {
                        // Crear la petición para el usuario que la recibe
                        friendRequestDatabase.child(userId).child(currentUser.uid).child(NodePeticiones.REQUEST_TYPE)
                            .setValue(constants.REQUEST_STATUS_RECEIVED).addOnCompleteListener {

                                if (it.isSuccessful) {
                                    Toast.makeText(context, context.getString(R.string.request_sent_succesfully), Toast.LENGTH_SHORT).show()
                                    holder.btnSendRequest.visibility = View.GONE
                                    holder.pbRequest.visibility = View.GONE
                                    holder.btnCancelRequest.visibility = View.VISIBLE

                                }

                                else {

                                    Toast.makeText(context, context.getString(R.string.failed_to_send_request, it.exception), Toast.LENGTH_SHORT).show()
                                    holder.btnSendRequest.visibility = View.VISIBLE
                                    holder.pbRequest.visibility = View.GONE
                                    holder.btnCancelRequest.visibility = View.GONE

                                }


                            }

                    }
                    else {

                        Toast.makeText(context, context.getString(R.string.failed_to_send_request, it.exception), Toast.LENGTH_SHORT).show()
                        holder.btnSendRequest.visibility = View.VISIBLE
                        holder.pbRequest.visibility = View.GONE
                        holder.btnCancelRequest.visibility = View.GONE

                    }
                }


        }

        holder.btnCancelRequest.setOnClickListener {
            holder.btnCancelRequest.visibility = View.GONE
            holder.pbRequest.visibility = View.VISIBLE

            userId = friendModel.getUserId()
            // Crear petición enviada
            friendRequestDatabase.child(currentUser.uid).child(userId).child(NodePeticiones.REQUEST_TYPE)
                .setValue(null).addOnCompleteListener { it ->
                    if (it.isSuccessful) {
                        // Crear la petición para el usuario que la recibe
                        friendRequestDatabase.child(userId).child(currentUser.uid).child(NodePeticiones.REQUEST_TYPE)
                            .setValue(null).addOnCompleteListener {

                                if (it.isSuccessful) {
                                    Toast.makeText(context, context.getString(R.string.request_cancelled_succesfully), Toast.LENGTH_SHORT).show()
                                    // cambiar la visibilidad a la inversa del btnSendRequest
                                    holder.btnSendRequest.visibility = View.VISIBLE
                                    holder.pbRequest.visibility = View.GONE
                                    holder.btnCancelRequest.visibility = View.GONE

                                }

                                else {

                                    Toast.makeText(context, context.getString(R.string.failed_to_cancel_request, it.exception), Toast.LENGTH_SHORT).show()
                                    holder.btnSendRequest.visibility = View.GONE
                                    holder.pbRequest.visibility = View.GONE
                                    holder.btnCancelRequest.visibility = View.VISIBLE

                                }


                            }

                    }
                    else {

                        Toast.makeText(context, context.getString(R.string.failed_to_cancel_request, it.exception), Toast.LENGTH_SHORT).show()
                        holder.btnSendRequest.visibility = View.GONE
                        holder.pbRequest.visibility = View.GONE
                        holder.btnCancelRequest.visibility = View.VISIBLE

                    }
                }


        }


    }


}
