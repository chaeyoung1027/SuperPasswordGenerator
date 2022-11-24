package com.example.superpasswordgenerator

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import androidx.appcompat.app.AlertDialog

class PasswordSave  : AppCompatActivity() {
    var firestore : FirebaseFirestore? = null
    var saveList : MutableList<Save> = mutableListOf<Save>()
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_save)

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


    }
}