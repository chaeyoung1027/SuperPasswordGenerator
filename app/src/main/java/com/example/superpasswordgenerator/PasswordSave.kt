package com.example.superpasswordgenerator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

data class password(var password : String? = null)

class PasswordSave  : AppCompatActivity() {

    var firestore : FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_save)

        firestore = FirebaseFirestore.getInstance()
    }
}