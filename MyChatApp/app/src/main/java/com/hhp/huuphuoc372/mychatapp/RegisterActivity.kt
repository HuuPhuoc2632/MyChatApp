package com.hhp.huuphuoc372.mychatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        // Initialize Firebase Auth
        auth = Firebase.auth

        val edtemail = findViewById<EditText>(R.id.edtUser)
        val edtpassword = findViewById<EditText>(R.id.edtPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val email: String = edtemail.text.toString().trim()
            val password: String = edtpassword.text.toString().trim()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Sucecssfull", Toast.LENGTH_LONG).show()
                        val firebaseUser = FirebaseAuth.getInstance().currentUser
                        val userId: String? = firebaseUser?.uid

                        val reference = FirebaseDatabase.getInstance().getReference("User").child(userId!!)

                        val hm = HashMap<String, String>()
                        hm["id"] = userId.toString()
                        hm["user"] = email.toString()
                        hm["imageURL"] = "default"
                        reference.setValue(hm).addOnCompleteListener {
                            goToLoginActivity()
                        }
                    } else {
                        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                    }
                }
        }

    }
    private fun goToLoginActivity(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

}