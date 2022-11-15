package com.example.superpasswordgenerator

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import com.google.firebase.database.DatabaseReference

private lateinit var database: DatabaseReference

class PasswordGenerator {
    private var characterSet: MutableSet<Char> = mutableSetOf()

    //문자열 추가하기
    fun feed(ingredients: String) {
        for(c in ingredients) characterSet.add(c)
    }

    //비밀번호 만들기
    fun generate(length: Int): String {
        var result = ""
        for(i in 0 until length) {
            result += characterSet.random()
        }
        return result
    }

    //characterSet 초기화 하기
    fun clear() {
        // characterSet = mutableSetOf()
        characterSet.clear()
    }

    //클래스 상수
    companion object {
        val ALPHABET_UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val ALPHABET_LOWERCASE = "abcdefghijklmnopqrstuvwxyz"
        val DIGITS = "0123456789"
    }
}

class PasswordGenerate  : AppCompatActivity(){
    private val generator = PasswordGenerator()
    lateinit var MaxV : Slider
    lateinit var MinV : Slider
    lateinit var rg : RadioGroup
    lateinit var spec: LinearLayout
    lateinit var NumberCheck: CheckBox
    lateinit var result: TextView

    fun generatePassword() {
        generator.clear()
        val len = (MinV.value.toInt()..MaxV.value.toInt()).random()
        Log.d("mytag", len.toString())
        when(rg.checkedRadioButtonId) {
            R.id.all -> {
                generator.feed(PasswordGenerator.ALPHABET_LOWERCASE)
                generator.feed(PasswordGenerator.ALPHABET_UPPERCASE)
            }
            R.id.lower -> {
                generator.feed(PasswordGenerator.ALPHABET_LOWERCASE)
            }
            R.id.upper -> {
                generator.feed(PasswordGenerator.ALPHABET_UPPERCASE)
            }
        }
        for (i in 1 until spec.childCount) {
            val checkBox = spec.getChildAt(i) as CheckBox
            if(checkBox.isChecked) generator.feed(checkBox.text.toString())
        }
        if(NumberCheck.isChecked) generator.feed(PasswordGenerator.DIGITS)
        val password = generator.generate(len)
        result.text = password
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_generate)

        MaxV = findViewById<Slider>(R.id.max_v)                     //비밀번호 길이(Max)
        MinV = findViewById<Slider>(R.id.min_v)                     //비밀번호 길이(Min)
        result = findViewById<TextView>(R.id.result)                //결과를 보여주는 칸
        val GenBtn = findViewById<Button>(R.id.password_generate_Btn)   //생성하기 버튼
        val SavePassword = findViewById<Button>(R.id.password_save_Btn) //저장하기 버튼
        val SelectAll = findViewById<TextView>(R.id.select_all)         //특수문자 전체 선택
        rg = findViewById<RadioGroup>(R.id.rg)                      //영어 포함 여부 Radio 버튼
        NumberCheck = findViewById<CheckBox>(R.id.numbercheck)      //숫자 포함 여부 CheckBox

        //최대글자 최소글자
        MaxV.addOnChangeListener { slider, value, fromUser ->   //최소글자가 최대글자를 넘어가면 최대글자도 늘어남, 최대글자가 줄어들어도 같다
            if(MaxV.value<MinV.value) {
                MinV.value=MaxV.value
            }
            result.setFilters(arrayOf<InputFilter>(LengthFilter(value.toInt())))    //MaxLength 조절하는 코드
        }
        MinV.addOnChangeListener { slider, value, fromUser ->
            if(MaxV.value<MinV.value) {
                MaxV.value=MinV.value
            }
        }

        //생성하기 버튼
        GenBtn.setOnClickListener{
            SavePassword.visibility = View.VISIBLE
            generatePassword()
        }

        //특수문자 전체 선택
        var checked = false

        spec = findViewById<LinearLayout>(R.id.sh)

        SelectAll.setOnClickListener {
            checked = !checked
            for (i in 1 until spec.childCount) {
                val checkBox = spec.getChildAt(i) as CheckBox
                checkBox.isChecked = checked
            }
        }

        SavePassword.setOnClickListener {
            val intent = Intent(this, PasswordManage::class.java)
            intent.putExtra("password", result.text.toString())
            startActivity(intent)
        }
    }
}