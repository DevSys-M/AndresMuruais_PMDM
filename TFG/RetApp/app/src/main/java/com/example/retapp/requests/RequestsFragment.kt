package com.example.retapp.requests

import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retapp.R
import com.example.retapp.common.Constants
import com.example.retapp.common.NodeNames
import com.example.retapp.findfriends.FindFriendModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*


class RequestsFragment : Fragment() {


    private lateinit var rvRequests: RecyclerView
    private lateinit var adapter: RequestAdapter
    // lista de fetch del nodo peticiones
    private lateinit var requestModelList: MutableList<RequestModel>
    // El Textview que al principio dice "Aquí aparecerá la lista..."
    private lateinit var tvEmptyRequestsList: TextView

    // dbref para sacar la lista de usuarios
    private lateinit var databaseReferenceRequests: DatabaseReference
    private lateinit var databaseReferenceUsers: DatabaseReference
    private lateinit var currentUser: FirebaseUser
    private lateinit var progressBar: View



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_requests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvRequests = view.findViewById(R.id.rvRequests)
        tvEmptyRequestsList = view.findViewById(R.id.tvEmptyRequestsList)

        progressBar = view.findViewById(R.id.progressBar)


        // inicializar lista y adaptador construyendolo en la pantalla con la lista
        requestModelList = mutableListOf()
        adapter = RequestAdapter(requireActivity(), requestModelList)

        rvRequests.layoutManager = LinearLayoutManager(activity)

        rvRequests.adapter = adapter

        // sacar usuario actual
        var Nodes: NodeNames = NodeNames()
        currentUser = FirebaseAuth.getInstance().currentUser!!
        // sacar el nodo de lista de usuarios para acceder a la foto, nombre etc.
        databaseReferenceUsers = FirebaseDatabase.getInstance().reference.child(Nodes.USERS)
        // sacar el nodo de peticiones
        databaseReferenceRequests = FirebaseDatabase.getInstance().getReference().child(Nodes.FRIEND_REQUESTS).child(currentUser.uid)

        // mostrar estos dos elementos al principio de la carga
        tvEmptyRequestsList.visibility = View.VISIBLE
        progressBar.visibility = View.VISIBLE


        databaseReferenceRequests.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var constants: Constants = Constants()
                progressBar.visibility = View.GONE
                // para que si ya se mostraba la lista, no se duplique
                (requestModelList as MutableList<RequestModel>).clear()

                // bucle que recorre nodo
                for (ds in snapshot.children) {

                    if (ds.exists()) {
                        // sacar el tipo de petición. Aquí nos interesan los de tipo RECEIVED
                        var requestType: String = ds.child(Nodes.REQUEST_TYPE).value.toString()
                        if (requestType == constants.REQUEST_STATUS_RECEIVED) {

                            var userId: String = ds.key!!
                            databaseReferenceUsers.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    // sacamos los datos del usuario, crear en objeto y meter en lista
                                    var userName: String = snapshot.child(Nodes.NAME).value.toString()
                                    var photoName: String = ""
                                    if (snapshot.child(Nodes.PHOTO).value != null) {
                                        photoName =   "images/"+userId+".jpg" // snapshot.child(Nodes.PHOTO).value.toString() //
                                    }

                                    var requestModel: RequestModel = RequestModel(userName, photoName, userId)
                                    // añadir objeto creado en la lista
                                    (requestModelList as MutableList<RequestModel>).add(requestModel)
                                    adapter.notifyDataSetChanged()

                                    // ocultar tv
                                    tvEmptyRequestsList.visibility = View.GONE



                                }

                                override fun onCancelled(error: DatabaseError) {                                        // placeholder -> mensaje
                                    Toast.makeText(context, context!!.getString(R.string.failed_to_fetch_friend_requests, error.message), Toast.LENGTH_SHORT).show()
                                    tvEmptyRequestsList.visibility = View.GONE

                                }


                            })

                        }
                    }


                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, context!!.getString(R.string.failed_to_fetch_friend_requests, error.message), Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
            }


        })


    }





}