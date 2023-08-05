package com.hhp.huuphuoc372.mychatapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hhp.huuphuoc372.mychatapp.Model.User
import com.hhp.huuphuoc372.mychatapp.R
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter(var context: Context, var userArray: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imUser: CircleImageView = itemView.findViewById(R.id.cimvUser)
        val tvUserName: TextView = itemView.findViewById(R.id.userName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userArray.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.tvUserName.text = userArray[position].user
        if (userArray[position].imageURL.toString().equals("default")) {
            holder.imUser.setImageResource(R.drawable.avt)
        } else {
            Glide.with(context).load(userArray[position].imageURL).into(holder.imUser)
        }
    }
}