package com.example.superpasswordgenerator

import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider


class PasswordGenerate  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_generate)
        Log.d("mytag", "??")
        val SelectAll = findViewById<TextView>(R.id.select_all)
        val MaxV = findViewById<Slider>(R.id.max_v)
        val MinV = findViewById<Slider>(R.id.min_v)
        val ResultLength = findViewById<EditText>(R.id.result)
        val Spec = findViewById<CheckBox>(R.id.sh)

        MaxV.addOnChangeListener { slider, value, fromUser ->
            if(MaxV.value<MinV.value) {
                MinV.value=MaxV.value
            }
            Log.d("mytag", MinV.value.toInt().toString())
            Log.d("mytag", value.toInt().toString())

            ResultLength.setFilters(arrayOf<InputFilter>(LengthFilter(value.toInt())))//MaxLength 조절하는 코드
        }
        MinV.addOnChangeListener { slider, value, fromUser ->
            if(MaxV.value<MinV.value) {
                MaxV.value=MinV.value
            }
        }

        var i =0
        //전체 선택
        SelectAll.setOnClickListener{
            if(i%2==0)Spec.isChecked = true
            else Spec.isChecked = false
            //checkbox를 List로 바꿀 수 있는지
            //전체 선택을 전체 취소로 바꿀 수 있는지(하나라도 false면)
        }
    }
}