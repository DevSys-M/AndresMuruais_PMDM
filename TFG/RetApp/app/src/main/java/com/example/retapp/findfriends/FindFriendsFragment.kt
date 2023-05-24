package com.example.retapp.findfriends

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retapp.R
import com.example.retapp.common.NodeNames
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class FindFriendsFragment : Fragment() {

    private lateinit var rvFindFriends: RecyclerView
    private lateinit var findFriendAdapter: FindFriendAdapter
    // Lista para hacer fetch del Firebase
    private lateinit var findFriendModelList: List<FindFriendModel>
    // El Textview que al principio dice "Aquí aparecerá la lista..."
    private lateinit var tvEmptyFriendsList: TextView

    // dbref para sacar la lista de usuarios
    private lateinit var databaseReference: DatabaseReference
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
        rvFindFriends.layoutManager = LinearLayoutManager(requireActivity())
        // inicializar lista
        findFriendModelList = ArrayList() // <FriendModel..?>
        // inicializar adapter
        findFriendAdapter = FindFriendAdapter(requireActivity(), findFriendModelList)
        // darle recyclerView al adapter
        rvFindFriends.adapter = findFriendAdapter

        // sacar todos los usuarios
        var Nodes: NodeNames = NodeNames()
        databaseReference = FirebaseDatabase.getInstance().getReference().child(Nodes.USERS)
        currentUser = FirebaseAuth.getInstance().currentUser!!

        // Sacar el textView de primeras. Si hay datos en la dbRef se esconderá
        tvEmptyFriendsList.visibility = View.VISIBLE
        progressBar.visibility = View.VISIBLE

        // Sacar usuarios en orden alfabetico
        var query: Query = databaseReference.orderByChild(Nodes.NAME)
        query.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                (findFriendModelList as ArrayList<FindFriendModel>).clear()
                for (ds: DataSnapshot in snapshot.children) {
                    var userId: String = ds.key!!

                    // excluir al propio usuario de la lista de peticiones de contactos ( no se puede agregar a si mismo)
                    if (userId.equals(currentUser.uid)) {
                        return
                    }

                    // si el nombre no es nulo
                    if (ds.child(Nodes.NAME).value != null) {
                        var fullName: String = ds.child(Nodes.NAME).value.toString()
                        var photoName: String = ds.child(Nodes.PHOTO).value.toString()

                        (findFriendModelList as ArrayList<FindFriendModel>).add(FindFriendModel(fullName,photoName, userId, false))
                        findFriendAdapter.notifyDataSetChanged()

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

