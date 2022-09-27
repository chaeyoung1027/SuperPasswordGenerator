package com.example.superpasswordgenerator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

//비밀번호 관리 페이지
class PasswordManage : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_manage)

        val site = findViewById<EditText>(R.id.site_text)
        val save = findViewById<Button>(R.id.save_btn)
        val password = findViewById<TextView>(R.id.password)

        password.text = intent.getStringExtra("password").toString()

        save.setOnClickListener {
            val edit_site = site.text.toString()
            val pref = getSharedPreferences("password", Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString("site", edit_site)
            editor.apply()

            Log.d("mytag", editor.toString())
        }

    }
}