package com.example.superpasswordgenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

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

//1. 기본 페이지(시작페이지, 비밀번호 생성&저장&관리 페이지) 만들기
//2. 설정(최솟값, 최댓값 설정,  만들기
//3. 처음 들어갈 때 비밀번호 입력할 수 있게 하기
//4. 시간이 된다면 글자수가 바뀌면 결과 표시창도바뀌게하기