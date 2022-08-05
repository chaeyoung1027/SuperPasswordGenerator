package com.example.superpasswordgenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val GenBtn = findViewById<Button>(R.id.password_generate_Btn)
        val SaveBtn = findViewById<Button>(R.id.password_save_Btn)

        //비밀번호 생성 버튼
        GenBtn.setOnClickListener {
            val intent = Intent(this, PasswordGenerate::class.java)
            startActivity(intent)
        }

        //비밀번호 저장 버튼
        SaveBtn.setOnClickListener {
            val intent = Intent(this, PasswordSave::class.java)
            startActivity(intent)
        }

    }


}