package com.example.superpasswordgenerator

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.GridLayout.spec
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.google.android.material.slider.Slider
import com.google.firebase.database.DatabaseReference

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
        var NumberCheck = findViewById<CheckBox>(R.id.numbercheck)
        val rg = findViewById<RadioGroup>(R.id.rg)
        var Upperlist = listOf("A","B","C","D","E","F","G","H","I","J","K","L","N","M","O","P","Q","R","S","T","U", "V", "W", "X", "Y", "Z")
        var Lowerlist = listOf("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u", "v", "w", "x", "y", "z")
        var SpecChar = listOf("!", "@", "#", "$","^","&","*")
        var Number = listOf("1", "2", "3", "4", "5", "6","7","8","9","0")
        val passArray = ArrayList<String>()


        //최대글자 최소글자
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

        //영어 포함
        rg.setOnCheckedChangeListener { group, checkedId -> //영어 포함 Radio 버튼 값을 받기
            when(checkedId){
                R.id.all -> {
                    mode = "all"
                    passArray.addAll(Upperlist)
                    passArray.addAll(Lowerlist)
                }
                R.id.upper ->{
                    mode = "upper"
                    passArray.addAll(Upperlist)
                }
                R.id.lower ->{
                    mode = "lower"
                    passArray.addAll(Lowerlist)
                }

            }
        }

        if (NumberCheck.isChecked)passArray.addAll(Number)

        NumberCheck.setOnClickListener { view ->

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
        for (i in 0 until spec.childCount) {
            if((spec.getChildAt(i) as CheckBox).isChecked)
                passArray.add(SpecChar[i])
        }

        SavePassword.setOnClickListener {
            val intent = Intent(this, PasswordManage::class.java)
            intent.putExtra("password", result.text.toString())
            startActivity(intent)
        }

    }
}