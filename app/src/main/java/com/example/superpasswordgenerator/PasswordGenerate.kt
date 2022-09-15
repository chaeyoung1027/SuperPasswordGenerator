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

        MaxV.addOnChangeListener { slider, value, fromUser ->
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

        fun generatepassword(count: Int) : String {    //랜덤 문자열 생성 함수
            val char = mutableListOf<Char>()
            for(i in 1..count) {
                char.add(('A'..'Z').random())
                char.add(('a'..'z').random())
                char.add('!')

            }
            return char.joinToString("")
        }

        GenBtn.setOnClickListener{
            result.text = generatepassword(10)  //TODO : 글자 수 받아서 변환되게 하기
            SavePassword.visibility = View.VISIBLE
        }


        //특수문자 전체 선택
        var checked = false

        // 1.
        val spec = findViewById<LinearLayout>(R.id.sh)

        SelectAll.setOnClickListener {
            checked = !checked

            for (i in 1 until spec.childCount) {
                val checkBox = spec.getChildAt(i) as CheckBox
                checkBox.isChecked = checked
            }
        }

        var Upperlist = listOf("A","B","C","D","E","F","G","H","I","J","K","L","N","M","O","P","Q","R","S","T","U", "V", "W", "X", "Y", "Z")
        var Lowerlist = listOf("a", "b", "c", "d", "e", "f", "g","h", "i", "j","k","l","m","n","o","p","q",'r',"s", "t", "u", "v", "w", "x", "y", "z")
        var SpecChar = listOf("!", "@", "#", "$","^","&","*")
    }
}