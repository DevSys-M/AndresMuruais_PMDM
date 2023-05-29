package com.example.retapp.chat

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


class ChatFragment : Fragment() {
    private lateinit var rvChatList: RecyclerView
    private lateinit var progressBar: View
    private lateinit var tvEmptyChatList: TextView
    private lateinit var chatListAdapter: ChatListAdapter
    private lateinit var chatListModelList: MutableList<ChatListModel>

    // referencia de chats y el usuario actual
    private lateinit var databaseReferenceChats: DatabaseReference
    private lateinit var databaseReferenceUsers: DatabaseReference
    private lateinit var currentUser: FirebaseUser

    // refrescar la lista de mensajes cada vez que se meta / borre mensaje con addChild, RemoveChild...
    // como se va a refrescar cada dos por tres, mejor usar este listener
    private lateinit var childEventListener: ChildEventListener


    private lateinit var query: Query


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvChatList = view.findViewById(R.id.rvChats)
        tvEmptyChatList = view.findViewById(R.id.tvEmptyChatList)

        chatListModelList = mutableListOf()
        chatListAdapter = ChatListAdapter(requireActivity(), chatListModelList)


        var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(requireActivity())
        // query los datos basado en el último mensaje (timestamp)
        // mostar los records

        // dar la vuelta al recycler para que el último mensaje se muestre al principio
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true

        // enlazar laymanager al recyclerview y adaptador al recycler
        rvChatList.layoutManager = linearLayoutManager
        rvChatList.adapter = chatListAdapter

        progressBar = view.findViewById(R.id.progressBar)

        var nodes: NodeNames = NodeNames()

        // sacar la referencia de los nodos
        databaseReferenceUsers = FirebaseDatabase.getInstance().reference.child(nodes.USERS)
        currentUser = FirebaseAuth.getInstance().currentUser!!
        databaseReferenceChats = FirebaseDatabase.getInstance().reference.child(nodes.CHATS).child(currentUser.uid)

        // ordenar los registros timestamps en el nodo chats
        query = databaseReferenceChats.orderByChild(nodes.TIME_STAMP)

        // no escuchar cuando el fragment este onDestroyed
        childEventListener = object : ChildEventListener {
            // cuando me aceptan una request de amistad y mi chat screen esta on
            // isNew -> true
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {

                updateList(dataSnapshot, true, dataSnapshot.key!!)


            }
            // si me envian un mensaje. isNew -> false
            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
               // updateList(dataSnapshot, true, dataSnapshot.key!!)
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        };

        query.addChildEventListener(childEventListener)

        progressBar.visibility = View.VISIBLE
        tvEmptyChatList.visibility = View.VISIBLE


    }

    // Metodo comun a onChilAddes y OnChildChanged, ya que la finalidad es la misma
    private fun updateList(dataSnapshot: DataSnapshot, isNew: Boolean, userId: String ) {


        progressBar.visibility = View.GONE
        tvEmptyChatList.visibility = View.GONE


        var lastMessage: String
        var lastMessageTime: String
        var unreadCount: String
        lastMessage = ""
        lastMessageTime = ""
        unreadCount = ""

        // sacar nombre y foto del usuario
        var nodes: NodeNames = NodeNames()
        databaseReferenceUsers.child(userId).addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // comprobar que no sea Null, asignar por defecto vacío
                var fullName: String = snapshot.child(nodes.NAME).value?.toString() ?: "Sin Nombre"
                var photoName: String = ("images/" + userId + ".jpg"?.toString()) ?: ""

                // construir el objeto
                var chatListModel: ChatListModel = ChatListModel(userId, fullName, photoName, unreadCount, lastMessage, lastMessageTime)
                chatListModelList.add(chatListModel)
                chatListAdapter.notifyDataSetChanged()




            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, activity!!.getString(R.string.failed_to_fetch_chat_list, error.message), Toast.LENGTH_SHORT).show()

            }


        })


    }

    override fun onDestroy() {
        super.onDestroy()
        query.removeEventListener(childEventListener)
    }


}