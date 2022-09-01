package com.example.superpasswordgenerator

import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import java.util.Random;


class PasswordGenerate  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_generate)
        Log.d("mytag", "??")
        val SelectAll = findViewById<TextView>(R.id.select_all)
        val MaxV = findViewById<Slider>(R.id.max_v)
        val MinV = findViewById<Slider>(R.id.min_v)
        val result = findViewById<TextView>(R.id.result)
        val Spec1 = findViewById<CheckBox>(R.id.sh1)
        val Spec2 = findViewById<CheckBox>(R.id.sh2)
        val Spec3 = findViewById<CheckBox>(R.id.sh3)
        val Spec4 = findViewById<CheckBox>(R.id.sh4)
        val Spec5 = findViewById<CheckBox>(R.id.sh5)
        val Spec6 = findViewById<CheckBox>(R.id.sh6)
        val Spec7 = findViewById<CheckBox>(R.id.sh7)
        val GenBtn = findViewById<Button>(R.id.password_generate_Btn)

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

        fun UpperCase(){        //대문자 함수

        }
        fun LowerCase(){        //소문자 함수

        }
        fun SpecCase(){         //특수문자 함수

        }
        fun Num(){              //숫자 함수

        }

        GenBtn.setOnClickListener{
            result.text = generatepassword(10)  //TODO : 글자 수 받아서 변환되게 하기
        }





        var i = 0
        //전체 선택
        SelectAll.setOnClickListener{
            if(i%2==0){
                Spec1.isChecked = true
                Spec2.isChecked = true
                Spec3.isChecked = true
                Spec4.isChecked = true
                Spec5.isChecked = true
                Spec6.isChecked = true
                Spec7.isChecked = true
            }
            else{
                Spec1.isChecked = false
                Spec2.isChecked = false
                Spec3.isChecked = false
                Spec4.isChecked = false
                Spec5.isChecked = false
                Spec6.isChecked = false
                Spec7.isChecked = false
            }
            //checkbox를 List로 바꿀 수 있는지
            //전체 선택을 전체 취소로 바꿀 수 있는지(하나라도 false면)
            i++
        }
    }
}