package com.example.superpasswordgenerator

import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class PasswordSave  : AppCompatActivity() {

    var firestore : FirebaseFirestore? = null
    var saveList = arrayListOf<Save>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_save)

        val password = findViewById<TextView>(R.id.pw)
        val id = findViewById<TextView>(R.id.id)
        val site = findViewById<TextView>(R.id.site)
        val password_list = findViewById<ListView>(R.id.password_list)

        intent.getStringExtra("password")
        intent.getStringExtra("site")
        intent.getStringExtra("id")

        val saveListAdapter = SaveListAdapter(this, saveList)
        password_list.adapter = saveListAdapter

    }
}