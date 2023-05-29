package com.example.retapp.chat

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retapp.R
import com.example.retapp.common.Constants
import com.example.retapp.common.Extras
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class ChatListAdapter(private val context: Context, private var chatListModelList: MutableList<ChatListModel>) : RecyclerView.Adapter<ChatListAdapter.ChatListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.chat_list_layout, parent, false)
        return ChatListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chatListModelList.size
    }

    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {
        var chatListModel = chatListModelList[position]

        val constants: Constants = Constants()
        holder.tvFullName.text = chatListModel.getUserName()

        // Similar a la lógica de RequestAdapter
        val storage: FirebaseStorage = FirebaseStorage.getInstance()
        val storageRef: StorageReference = storage.getReferenceFromUrl("gs://retapp-43e84.appspot.com")
        val photoName: String = chatListModel.getPhotoName()

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

        // necesitamos este LinearLay para acceder a la activity del chat
        holder.llChatList.setOnClickListener {
            var intent: Intent = Intent(context, ChatActivity::class.java)
            // pasar la id del usuario a la activity
            intent.putExtra(Extras().USER_KEY, chatListModel.getUserId() )
            context.startActivity(intent)

        }


    }
    // End bindViewHolda

    inner class ChatListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


            var llChatList: LinearLayout = itemView.findViewById(R.id.llChatList)
            var tvFullName: TextView = itemView.findViewById(R.id.tvFullName)
            var tvLastMessage: TextView = itemView.findViewById(R.id.tvLastMessage)
            var tvLastMessageTime: TextView = itemView.findViewById(R.id.tvLastMessageTime)
            var tvUnreadCount: TextView = itemView.findViewById(R.id.tvUnreadCount)
            var ivProfile: ImageView = itemView.findViewById(R.id.ivProfile)




    }


}