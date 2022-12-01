package com.example.superpasswordgenerator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

//비밀번호 관리 페이지

class PasswordManage : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_manage)

        val db = Firebase.firestore
        val site = findViewById<EditText>(R.id.site_text)
        val id = findViewById<EditText>(R.id.site_id)
        val save_button = findViewById<Button>(R.id.save_btn)
        val password = findViewById<TextView>(R.id.password)
        var saveList : MutableList<Save> = mutableListOf<Save>()

        val save : Save? = intent.getParcelableExtra<Save>("save")
        if(save == null) {
            password.text = intent.getStringExtra("password").toString()

            save_button.setOnClickListener {
                val siteURL = site.text.toString()

                val row = hashMapOf(
                    "password" to password.text.toString()
                )

                db.collection("passwords").document(siteURL + "#" + id.text.toString())
                    .set(row)
                    .addOnSuccessListener {
                        Log.d("mytag", "DocumentSnapshot successfully written!")
                        val intent = Intent(this, PasswordSave::class.java)
                        startActivity(intent)
                    }

                    .addOnFailureListener { e -> Log.w("mytag", "Error writing document", e) }


            }
        } else {
            id.isEnabled = false
            site.isEnabled = false
            password.text = save.password
            site.setText(save.site)
            id.setText(save.id)
            save_button.text = "수정하기"
            save_button.setOnClickListener {
                db.collection("passwords")
                    .document(save.site + "#" + save.id)
                    .update("password", password.text.toString())
                    .addOnSuccessListener {
                        Log.d("mytag", "success change")
                        Toast.makeText(this, "수정 완료", Toast.LENGTH_SHORT).show()
                        finish()
                    }
/*                db.collection("password").document(save.site + "#" + save.id)
                    .addSnapshotListener { snapshot, e ->
                        if (e != null) {
                            Log.w("mytag", "Listen failed.", e)
                            return@addSnapshotListener
                        }

                        if (snapshot != null && snapshot.exists()) {
                            Log.d("mytag", "Current data: ${snapshot.data}")
                        } else {
                            Log.d("mytag", "Current data: null")
                        }
                    }*/
            }
        }
    }
}