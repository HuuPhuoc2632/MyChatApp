package com.hhp.huuphuoc372.mychatapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    lateinit var btnLogin: Button
    lateinit var tvRegister: TextView
    private lateinit var auth: FirebaseAuth
// ...


    override fun onCreate(savedInstanceState: Bundle?) {
        autoLogin()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mapping()

        tvRegister.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        val edtemail = findViewById<EditText>(R.id.edtUser)
        val edtpassword = findViewById<EditText>(R.id.edtPassword)
        edtemail.setText("huuphuoc372@gmail.com")
        edtpassword.setText("030702")
        // Initialize Firebase Auth
        auth = Firebase.auth
        btnLogin.setOnClickListener {
            val email: String = edtemail.text.toString().trim()
            val password: String = edtpassword.text.toString().trim()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show()
                        gotoMainActivity()
                    } else {
                        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                    }
                }

        }
    }

    private fun autoLogin() {
        val user = FirebaseAuth.getInstance().currentUser
        if(user!=null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun gotoMainActivity() {
        val user = FirebaseAuth.getInstance().currentUser
        val userId = user!!.uid

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("UserId", userId)
        startActivity(intent)

    }

    private fun mapping() {
        btnLogin = findViewById<Button>(R.id.btnLogin)
        tvRegister = findViewById(R.id.tvRegister)

    }

}