package com.example.superpasswordgenerator

import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.google.android.material.slider.Slider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private lateinit var database: DatabaseReference

class PasswordGenerate  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_generate)

        Log.d("mytag", "??")
        val SelectAll = findViewById<TextView>(R.id.select_all)
        val MaxV = findViewById<Slider>(R.id.max_v)
        val MinV = findViewById<Slider>(R.id.min_v)
        val result = findViewById<TextView>(R.id.result)
        val GenBtn = findViewById<Button>(R.id.password_generate_Btn)
        val SavePassword = findViewById<Button>(R.id.password_save_Btn)
        val rg = findViewById<RadioGroup>(R.id.rg)
        var Upperlist = listOf("A","B","C","D","E","F","G","H","I","J","K","L","N","M","O","P","Q","R","S","T","U", "V", "W", "X", "Y", "Z")
        var Lowerlist = listOf("a", "b", "c", "d", "e", "f", "g","h", "i", "j","k","l","m","n","o","p","q",'r',"s", "t", "u", "v", "w", "x", "y", "z")
        var SpecChar = listOf("!", "@", "#", "$","^","&","*")
        var Number = listOf("1", "2", "3", "4", "5", "6","7","8","9","0")
        val passArray = ArrayList<Int>()

        database = Firebase.database.reference
        //TODO: 저장 버튼 누르면 데이터 저장하기, 저장소에 데이터 읽어오기

        MaxV.addOnChangeListener { slider, value, fromUser ->   //최소글자가 최대글자를 넘어가면 최대글자도 늘어남, 최대글자가 줄어들어도 같다
            if(MaxV.value<MinV.value) {
                MinV.value=MaxV.value
            }
            Log.d("mytag", MinV.value.toInt().toString())
            Log.d("mytag", value.toInt().toString())

            result.setFilters(arrayOf<InputFilter>(LengthFilter(value.toInt())))//MaxLength 조절하는 코드
        }
        MinV.addOnChangeListener { slider, value, fromUser ->
            if(MaxV.value<MinV.value) {
                MaxV.value=MinV.value
            }
        }

        var mode = "all"

        rg.setOnCheckedChangeListener { group, checkedId -> //영어 포함 Radio 버튼 값을 받기
            when(checkedId){
                R.id.all -> {
                    mode = "all"
                }
                R.id.upper ->{
                    mode = "upper"
                }
                R.id.small ->{
                    mode = "small"
                }

            }
        }

        fun generatepassword(count: Int) : String {    //랜덤 문자열 생성 함수
            val char = mutableListOf<Char>()
            for(i in 1..count) {
                char.add(('A'..'Z').random())
                char.add(('a'..'z').random())
                char.add('!')

            }
            return char.joinToString("")
        }

        GenBtn.setOnClickListener{  //비밀번호 길이
            result.text = generatepassword(10)  //TODO : 글자 수 받아서 변환되게 하기
            SavePassword.visibility = View.VISIBLE
        }


        //특수문자 전체 선택
        var checked = false

        val spec = findViewById<LinearLayout>(R.id.sh)

        SelectAll.setOnClickListener {
            checked = !checked

            for (i in 1 until spec.childCount) {
                val checkBox = spec.getChildAt(i) as CheckBox
                checkBox.isChecked = checked
            }
        }

        SavePassword.setOnClickListener {

        }

    }
}