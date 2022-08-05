package com.example.superpasswordgenerator

import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider


class PasswordGenerate  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_generate)
        Log.d("mytag", "??")
        val SelectAllBtn = findViewById<TextView>(R.id.select_all)
        val MaxV = findViewById<Slider>(R.id.max_v)
        val MinV = findViewById<Slider>(R.id.min_v)
        val ResultLength = findViewById<EditText>(R.id.result)

        MaxV.addOnChangeListener { slider, value, fromUser ->
            if(MaxV.value<MinV.value) {
                MinV.value=MaxV.value
            }
            Log.d("mytag", MinV.value.toInt().toString())
            Log.d("mytag", value.toInt().toString())

            ResultLength.setFilters(arrayOf<InputFilter>(LengthFilter(value.toInt())))

        }
        MinV.addOnChangeListener { slider, value, fromUser ->
            if(MaxV.value<MinV.value) {
                MaxV.value=MinV.value
            }
        }
//        MaxV.setOnClickListener{
//            Log.d("mytag", MaxV.value.toInt().toString())
//            Log.d("mytag", MinV.value.toInt().toString())
//        }
//        MinV.setOnClickListener{
//            Log.d("mytag", MaxV.value.toInt().toString())
//            Log.d("mytag", MinV.value.toInt().toString())
//        }

        SelectAllBtn.setOnClickListener{

        }
    }
}