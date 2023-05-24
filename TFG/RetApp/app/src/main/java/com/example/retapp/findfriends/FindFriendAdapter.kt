package com.example.retapp.findfriends

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retapp.R
import com.example.retapp.common.Constants
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FindFriendAdapter(private var context: Context,
                        private var findFriendModelList: List<FindFriendModel>) : RecyclerView.Adapter<FindFriendAdapter.FindFriendViewHolder>() {


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
        var friendModel: FindFriendModel = findFriendModelList.get(position)

        holder.tvFullName.text = friendModel.getUserName()
        // acceder a la foto de perfil

        var constants: Constants = Constants()

        var fileRef: StorageReference = FirebaseStorage.getInstance().getReference().child(constants.IMAGES_FOLDER + "/" + friendModel.getPhotoName())
        // sacar la Url
        fileRef.downloadUrl.addOnSuccessListener {
            Glide.with(context)
                .load(it)
                .placeholder(R.drawable.default_profile)
                .error(R.drawable.default_profile)
                .into(holder.ivProfile)
        }



    }


}
