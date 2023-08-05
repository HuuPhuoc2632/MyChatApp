package com.hhp.huuphuoc372.mychatapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.hhp.huuphuoc372.mychatapp.Model.Message
import com.hhp.huuphuoc372.mychatapp.R
import de.hdodenhof.circleimageview.CircleImageView

class MessageAdapter(var context: Context, var messageArray: ArrayList<Message>, val imageURL: String) :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    val MSG_TYPE_RIGHT = 0
    val MSG_TYPE_LEFT = 1



    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imProfile = itemView.findViewById<CircleImageView>(R.id.profile_image)
        val tvChat = itemView.findViewById<TextView>(R.id.chatLine)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MessageAdapter.MessageViewHolder {
        if (viewType==MSG_TYPE_RIGHT){
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.chat_line_right, parent, false)
            return MessageViewHolder(itemView)
        }
        else{
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.chat_line_right, parent, false)
            return MessageViewHolder(itemView)
        }
    }

    override fun getItemCount(): Int {
        return messageArray.size
    }


    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messageArray[position]
        holder.tvChat.text = message.content
        if (imageURL.equals("default")) {
            holder.imProfile.setImageResource(R.drawable.avt)
        } else {
            Glide.with(context).load(imageURL).into(holder.imProfile)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentUser = FirebaseAuth.getInstance().currentUser
        return if(messageArray[position].senderId == currentUser?.uid){
            MSG_TYPE_RIGHT
        }else{
            MSG_TYPE_LEFT
        }
    }
}