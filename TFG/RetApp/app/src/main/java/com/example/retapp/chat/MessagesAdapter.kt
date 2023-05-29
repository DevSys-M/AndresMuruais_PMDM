package com.example.retapp.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.retapp.R
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*

class MessagesAdapter(private val context: Context, private var messageList: List<MessageModel>) : RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>() {

    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.message_layout, parent, false)
        return MessageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {

        var message: MessageModel = messageList[position]
        firebaseAuth = FirebaseAuth.getInstance()



        var currentUser: String = firebaseAuth.currentUser!!.uid

        var fromUserId = message.getMessageFrom()

        var sfd: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm")


        var dateTime: String = sfd.format(Date(message.getMessageTime()))
        // TODO mirar si esto puede dar errores
        var splitString: Array<String> = dateTime.split(" ").toTypedArray()
        var messageTime: String = splitString[1]

        if (fromUserId.equals(currentUser)) {
            holder.llSent.visibility = View.VISIBLE
            holder.llReceived.visibility = View.GONE
            holder.tvSentMessage.text = message.getMessage()
            holder.tvSentMessageTime.text = messageTime
        }

        else {

            holder.llReceived.visibility = View.VISIBLE
            holder.llSent.visibility = View.GONE
            holder.tvReceivedMessage.text = message.getMessage()
            holder.tvReceivedMessageTime.text = messageTime

        }


    }

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var llSent: LinearLayout = itemView.findViewById(R.id.llSent)
        var llReceived: LinearLayout = itemView.findViewById(R.id.llReceived)
        var tvSentMessage: TextView = itemView.findViewById(R.id.tvSentMessage)
        var tvSentMessageTime: TextView = itemView.findViewById(R.id.tvSentMessageTime)
        var tvReceivedMessage: TextView = itemView.findViewById(R.id.tvReceivedMessage)
        var tvReceivedMessageTime: TextView = itemView.findViewById(R.id.tvReceivedMessageTime)
        var clMessage: ConstraintLayout =  itemView.findViewById(R.id.clMessage)




    }



}