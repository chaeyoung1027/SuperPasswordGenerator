package com.example.superpasswordgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_generate)


        val GenBtn = findViewById<Button>(R.id.password_generate_Btn)
        val SaveBtn = findViewById<Button>(R.id.password_save_Btn)

        //비밀번호 생성 버튼
        GenBtn.setOnClickListener {

        }
        //비밀번호 저장 버튼
        SaveBtn.setOnClickListener {

        }

    }


}