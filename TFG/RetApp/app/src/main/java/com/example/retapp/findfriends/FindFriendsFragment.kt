package com.example.retapp.findfriends

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retapp.R
import com.example.retapp.common.Constants
import com.example.retapp.common.NodeNames
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class FindFriendsFragment : Fragment() {

    private lateinit var rvFindFriends: RecyclerView
    private lateinit var findFriendAdapter: FindFriendAdapter
    // Lista para hacer fetch del Firebase
    private lateinit var findFriendModelList: MutableList<FindFriendModel>
    // El Textview que al principio dice "Aquí aparecerá la lista..."
    private lateinit var tvEmptyFriendsList: TextView



    // dbref para sacar la lista de usuarios
    private lateinit var databaseReference: DatabaseReference
    // ref de peticiones de amistad
    private lateinit var databaseReferenceFriendRequests: DatabaseReference
    private lateinit var currentUser: FirebaseUser

    private lateinit var progressBar: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find_friends, container, false)
    }

    // sobreescribir metodo e inicializar elementos
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvFindFriends = view.findViewById(R.id.rvFindFriends)
        progressBar = view.findViewById(R.id.progressBar)
        tvEmptyFriendsList = view.findViewById(R.id.tvEmptyFriendsList)
        // inicializar lay. del recycler
        rvFindFriends.layoutManager = LinearLayoutManager(activity)
        // inicializar lista
        findFriendModelList = mutableListOf() // <FriendModel..?>
        // inicializar adapter
        findFriendAdapter = FindFriendAdapter(requireActivity(), findFriendModelList)
        // darle recyclerView al adapter
        rvFindFriends.adapter = findFriendAdapter

        // sacar todos los usuarios
        var Nodes: NodeNames = NodeNames()
        databaseReference = FirebaseDatabase.getInstance().reference.child(Nodes.USERS)
        currentUser = FirebaseAuth.getInstance().currentUser!!

        // sacar peticiones de amistad para el usuario actual
        databaseReferenceFriendRequests = FirebaseDatabase.getInstance().getReference().child(Nodes.FRIEND_REQUESTS).child(currentUser.uid)

        // Sacar el textView de primeras. Si hay datos en la dbRef se esconderá
        tvEmptyFriendsList.visibility = View.VISIBLE
        progressBar.visibility = View.VISIBLE

        // Sacar usuarios en orden alfabetico
        var query: Query = databaseReference.orderByChild(Nodes.NAME)
        query.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                (findFriendModelList as MutableList<FindFriendModel>).clear()
                for (ds in snapshot.children) {
                    var userId: String = ds.key!!

                    // excluir al propio usuario de la lista de peticiones de contactos ( no se puede agregar a si mismo)
                    if (userId == currentUser.uid) {
                        continue
                    }

                    // si el nombre no es nulo
                    if (ds.child(Nodes.NAME).value != null) {
                        val fullName: String = ds.child(Nodes.NAME).value.toString()
                        val photoName: String = "images/"+userId+".jpg" //ds.child(Nodes.PHOTO).value.toString()

                        // escuchar un solo registro para las peticiones existentes
                                                        // el usuario al que envío o del que recibo la request
                        databaseReferenceFriendRequests.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                var constants: Constants = Constants()
                                // si hay registro en nodo friend requests
                                if (snapshot.exists()) {
                                    // ver si la request es sent o received
                                    var requestType: String = snapshot.child(Nodes.REQUEST_TYPE).value.toString()
                                    if (requestType.equals(constants.REQUEST_STATUS_SENT)) {                            // evitar que se reinicie el botón de request al salir de la app
                                        (findFriendModelList as ArrayList<FindFriendModel>).add(FindFriendModel(fullName,photoName, userId, true))
                                        findFriendAdapter.notifyDataSetChanged()

                                    }
                                }
                                else {
                                    // el registro no existe, requestsent = false -> boton enviar solicitud visible
                                    (findFriendModelList as ArrayList<FindFriendModel>).add(FindFriendModel(fullName,photoName, userId, false))
                                    findFriendAdapter.notifyDataSetChanged()

                                }

                            }

                            override fun onCancelled(error: DatabaseError) {

                                progressBar.visibility = View.GONE
                            }


                        })


                        // si hay datos, esconder el tv
                        tvEmptyFriendsList.visibility = View.GONE
                        progressBar.visibility = View.GONE

                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                progressBar.visibility = View.GONE                                          // excepcion
                Toast.makeText(context, context!!.getString(R.string.failed_to_fetch_friends, error.message), Toast.LENGTH_SHORT).show()
            }

        })


    }
}

