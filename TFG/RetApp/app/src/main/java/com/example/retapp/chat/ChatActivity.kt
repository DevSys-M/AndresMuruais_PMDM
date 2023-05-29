package com.example.retapp.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.retapp.R
import com.example.retapp.common.Constants
import com.example.retapp.common.Extras
import com.example.retapp.common.NodeNames
import com.example.retapp.common.Util
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class ChatActivity : AppCompatActivity(), View.OnClickListener {


    // nombre del usuario chat
    private lateinit var chatUserName: String

    private lateinit var ivSend: ImageView
    private lateinit var  etMessage: EditText
    // nodo raiz
    private lateinit var mRootRef: DatabaseReference
    private lateinit var fireaseAuth: FirebaseAuth

    private lateinit var currentUserId: String
    private lateinit var chatUserId: String

    // RV y swipe
    private lateinit var rvMessages: RecyclerView
    private lateinit var srlMessages: SwipeRefreshLayout
    private lateinit var messagesAdapter: MessagesAdapter
    private lateinit var messagesList: MutableList<MessageModel>

    // cargar mensajes segun va haciendo swipe, solo 30 mensajes de preview para que no pete
        // el usuario va deslizando la pantalla y se va incrementando este número
    private var currentPage: Int = 1
    private val RECORD_PER_PAGE: Int = 30

    private lateinit var databaseReferenceMessages: DatabaseReference
    // listener para registrar nuevos mensajes en FB
    private var childEventListener: ChildEventListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        ivSend = findViewById(R.id.ivSend)
        etMessage = findViewById(R.id.etMessage)

        ivSend.setOnClickListener(this)

        fireaseAuth = FirebaseAuth.getInstance()
        mRootRef = FirebaseDatabase.getInstance().reference
        currentUserId = fireaseAuth.currentUser!!.uid

        // comprobar que el user key se pasa a la Activity
        if (intent.hasExtra(Extras().USER_KEY)) {
            // obtener el chatUserId
            chatUserId = intent.getStringExtra(Extras().USER_KEY)!!

            val usersRef = mRootRef.child(NodeNames().USERS)
            usersRef.child(chatUserId).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // User exists, retrieve the user name
                        chatUserName = dataSnapshot.child(NodeNames().NAME).value.toString()
                        supportActionBar!!.title = chatUserName
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle error
                }
            })


        }




        // Implementar conversacion
        rvMessages = findViewById(R.id.rvMessages)
        srlMessages = findViewById(R.id.srlMessages)

        messagesList = mutableListOf()
        messagesAdapter = MessagesAdapter(this, messagesList)

        rvMessages.layoutManager = LinearLayoutManager(this)
        rvMessages.adapter = messagesAdapter

        loadMessages()
        rvMessages.scrollToPosition(messagesList.size - 1)

        // escuchador se llama cuando el usuario haga swipe
        srlMessages.setOnRefreshListener {
            currentPage++
            loadMessages()
        }


    }

    private fun sendMessage(msg: String, msgType: String, pushId: String) {
        try {
            if (!msg.equals("")) {
                // Hashmap para guardar datos - mensaje EN NODOS
                val messageMap: HashMap<String, Any> = HashMap()
                messageMap[NodeNames().MESSAGE_ID] = pushId
                messageMap[NodeNames().MESSAGE] = msg
                messageMap[NodeNames().MESSAGE_TYPE] = msgType
                messageMap[NodeNames().MESSAGE_FROM] = currentUserId
                messageMap[NodeNames().MESSAGE_TIME] = ServerValue.TIMESTAMP

                // crear nodos swapped
                val currentUserRef = "${NodeNames().MESSAGES}/$currentUserId/$chatUserId"
                val chatUserRef = "${NodeNames().MESSAGES}/$chatUserId/$currentUserId"

                val messageUserMap: HashMap<String, Any> = HashMap()
                // nodos swapped
                messageUserMap["$currentUserRef/$pushId"] = messageMap
                messageUserMap["$chatUserRef/$pushId"] = messageMap

                etMessage.setText("")
                mRootRef.updateChildren(messageUserMap, DatabaseReference.CompletionListener { databaseError, databaseReference ->
                    if (databaseError != null) {
                        // Error
                        Toast.makeText(this@ChatActivity, getString(R.string.failed_to_send_message, databaseError.message),
                            Toast.LENGTH_SHORT).show()
                    } else {
                        // Exito
                        Toast.makeText(this@ChatActivity, getString(R.string.message_sent_successfully),
                            Toast.LENGTH_SHORT).show()
                    }
                })
            }
        } catch (ex: Exception) {
            Toast.makeText(this@ChatActivity, getString(R.string.failed_to_send_message, ex.message),
                Toast.LENGTH_SHORT).show()
        }
    }


    // Método para sacar mensajes
    private fun loadMessages() {

        messagesList.clear()                        // Nodos Mensajes -> ID usuario -> id Chat
        databaseReferenceMessages = mRootRef.child(NodeNames().MESSAGES).child(currentUserId).child(chatUserId)

        // limitar últimos 30 mensajes. Cuando el usuario haga swipe, currentPage +1 (2*30) carga otros 30 etc
        var messageQuery: Query = databaseReferenceMessages.limitToLast(currentPage * RECORD_PER_PAGE)

        childEventListener?.let { listener ->
            messageQuery.removeEventListener(listener)
        }


        childEventListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                // convertir el snapshot directamente en el Model y crea los atributos del Nodo
               var message: MessageModel = snapshot.getValue(MessageModel::class.java)!!
                // el MessageModel necesita un constructor vacío para evitar RuntimeException

                messagesList.add(message)
                messagesAdapter.notifyDataSetChanged()
                // Mostrar el último mensaje en el bottom, luego si el usuario quiere ver los anteriores va haciendo swipe hacia arriba
                rvMessages.scrollToPosition(messagesList.size - 1)

                srlMessages.isRefreshing = false
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val changedMessage: MessageModel? = snapshot.getValue(MessageModel::class.java)
                val messageKey: String = snapshot.key!!

                if (changedMessage != null && messageKey != null) {
                    // Find the corresponding message in your messagesList and update it
                    val messageIndex: Int = messagesList.indexOfFirst { message ->
                        message.getMessageId() == messageKey
                    }

                    if (messageIndex != -1) {
                        messagesList[messageIndex] = changedMessage
                        messagesAdapter.notifyItemChanged(messageIndex)
                    }
                }
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
               srlMessages.isRefreshing = false
            }


        };

        messageQuery.addChildEventListener(childEventListener as ChildEventListener)

    }

    override fun onClick(v: View?) {

        when (v!!.id) {
            R.id.ivSend -> {

                // comprobar conexion a internet
                if (Util().connectionAvailable(this)) {
                    // generar pushId antes de llamar al método
                    // genera una id de mensaje única
                    var userMessagePush: DatabaseReference =
                        mRootRef
                            .child(NodeNames().MESSAGES)
                            .child(currentUserId)
                            .child(chatUserId)
                            .push()
                    // pillar pishId con el key
                    var pushId: String = userMessagePush.key!!
                    sendMessage(
                        etMessage.text.toString().trim(),
                        Constants().MESSAGE_TYPE_TEXT,
                        pushId
                    )
                }
                else {

                    Toast.makeText(this, R.string.no_internet, Toast.LENGTH_SHORT).show()
                }

            }
            // fin primer case


        }
        // switch


    }
}