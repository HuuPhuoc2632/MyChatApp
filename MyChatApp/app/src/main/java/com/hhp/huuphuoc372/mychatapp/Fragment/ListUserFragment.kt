package com.hhp.huuphuoc372.mychatapp.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hhp.huuphuoc372.mychatapp.Adapter.UserAdapter
import com.hhp.huuphuoc372.mychatapp.Model.User
import com.hhp.huuphuoc372.mychatapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListUserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListUserFragment : Fragment(R.layout.fragment_list_user) {

    var userArray: ArrayList<User> = ArrayList<User>()

    private fun readUser(){
        val firebaseUSer = FirebaseAuth.getInstance().currentUser
        val reference = FirebaseDatabase.getInstance().getReference("User")

        reference.addValueEventListener(object : ValueEventListener {

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                userArray.clear()
                if (snapshot.exists()) {
                    for (dataSnapshot in snapshot.children) {
                        val user = dataSnapshot.getValue(User::class.java)
                        if (!(user?.id.equals(firebaseUSer?.uid))) {
                            userArray.add(user!!)
                        }
                    }

                }
                userAdapter = UserAdapter(context!!, userArray)
                recyclerView.adapter = userAdapter
                userAdapter?.notifyDataSetChanged()
                layoutManager = LinearLayoutManager(context)
                recyclerView.layoutManager = layoutManager
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    lateinit var recyclerView: RecyclerView
    var layoutManager: RecyclerView.LayoutManager? = null
    var userAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



        recyclerView = view.findViewById<RecyclerView>(R.id.rvUser)
        recyclerView.setHasFixedSize(true)
        readUser()

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}

