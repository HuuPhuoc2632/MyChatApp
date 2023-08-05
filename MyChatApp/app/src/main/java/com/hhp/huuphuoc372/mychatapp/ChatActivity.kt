package com.hhp.huuphuoc372.mychatapp

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val btnSend = findViewById<ImageButton>(R.id.btnSend)
        val edtMessage = findViewById<EditText>(R.id.edtMessage)


        btnSend.setOnClickListener(View.OnClickListener {
//            val message = edtMessage.text.toString()
//            sendMesaage()
        })


//        var recyclerView = findViewById<RecyclerView>(R.id.rvMessage)
//        recyclerView.setHasFixedSize(true)
//        var layoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = layoutManager
    }

    private fun sendMesaage(sender:String, receiver: String, message: String, time:String) {
        val reference = FirebaseDatabase.getInstance().reference
        var hashMap = HashMap<String, String>()
        hashMap["senderId"]= sender
        hashMap["receiverId"] = receiver
        hashMap["content"]= message
        hashMap["time"] = time

        reference.child("Chats").push().setValue(hashMap)
    }
}