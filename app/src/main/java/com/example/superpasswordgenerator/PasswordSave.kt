package com.example.superpasswordgenerator

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class PasswordSave  : AppCompatActivity() {

    var firestore : FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_save)

        val password = findViewById<TextView>(R.id.pw)
        password.text = intent.getStringExtra("password").toString()

        var passwordList = arrayListOf<Save>()
    }
}