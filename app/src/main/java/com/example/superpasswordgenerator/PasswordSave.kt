package com.example.superpasswordgenerator

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PasswordSave  : AppCompatActivity() {
    var firestore : FirebaseFirestore? = null
    var saveList : MutableList<Save> = mutableListOf<Save>()
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_save)

        val password = findViewById<TextView>(R.id.pw)
        val id = findViewById<TextView>(R.id.id)
        val site = findViewById<TextView>(R.id.site)
        val password_list = findViewById<ListView>(R.id.password_list)

        val docRef = db.collection("passwords")
        docRef.get()
            .addOnSuccessListener { documents ->

                for (document in documents) {
                    Log.d("mytag", "${document.id} => ${document.data}")
                    var ids = document.id.split("#")
                    saveList.add(Save(document.data.get("password").toString(), ids[0], ids[1]))
                }

                Log.d("mytag", saveList.toString())

                val saveListAdapter = SaveListAdapter(this, saveList)
                password_list.adapter = saveListAdapter
            }
            .addOnFailureListener { exception ->
                Log.d("mytag", "get failed with ", exception)
            }

        /*
        password.text = intent.getStringExtra("password")
        site.text = intent.getStringExtra("site")
        id.text = intent.getStringExtra("id")

        saveList = arrayListOf<Save>()

        val saveListAdapter = SaveListAdapter(this, saveList)
        password_list.adapter = saveListAdapter


        val docRef = db.collection("passwords")
        docRef.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("mytag", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("mytag", "get failed with ", exception)
            }*/
    }
}