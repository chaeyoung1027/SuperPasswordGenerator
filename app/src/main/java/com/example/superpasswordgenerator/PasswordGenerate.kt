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
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet
import kotlin.random.Random.Default.nextInt

private lateinit var database: DatabaseReference

class PasswordGenerator {
    private var characterSet: MutableSet<Char> = mutableSetOf()

    //문자열 추가하기
    fun feed(ingredients: String) {
        for(c in ingredients) characterSet.add(c)
    }

    //비밀번호 길이 정하기
    fun length(Max:Int, Min:Int):Int{
        //val Max= Max
        //val Min= Min

        return (Min..Max).random()
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

    val MaxV = findViewById<Slider>(R.id.max_v)
    val MinV = findViewById<Slider>(R.id.min_v)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_generate)

        generator.feed(PasswordGenerator.ALPHABET_UPPERCASE)
        generator.feed(PasswordGenerator.ALPHABET_LOWERCASE)
        generator.feed(PasswordGenerator.DIGITS)


        /*
        val SelectAll = findViewById<TextView>(R.id.select_all)
        val result = findViewById<TextView>(R.id.result)
        val GenBtn = findViewById<Button>(R.id.password_generate_Btn)
        val SavePassword = findViewById<Button>(R.id.password_save_Btn)
        var NumberCheck = findViewById<CheckBox>(R.id.numbercheck)
        val rg = findViewById<RadioGroup>(R.id.rg)
        var SpecChar = listOf("!", "@", "#", "$","^","&","*")
        var passArray = ArrayList<String>()

        val generator = PasswordGenerator()


        //문자추가



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


        for (i in 1 until spec.childCount) {
            if((spec.getChildAt(i) as CheckBox).isChecked)
                passArray.add(SpecChar[i])
        }


        //최대글자 최소글자
        MaxV.addOnChangeListener { slider, value, fromUser ->   //최소글자가 최대글자를 넘어가면 최대글자도 늘어남, 최대글자가 줄어들어도 같다
            if(MaxV.value<MinV.value) {
                MinV.value=MaxV.value
            }

            result.setFilters(arrayOf<InputFilter>(LengthFilter(value.toInt())))//MaxLength 조절하는 코드
        }
        MinV.addOnChangeListener { slider, value, fromUser ->
            if(MaxV.value<MinV.value) {
                MaxV.value=MinV.value
            }
        }


        passArray = generator.feed(passArray)
        val length = generator.length(MinV.value.toInt(), MaxV.value.toInt())   //비밀번호의 길이

        GenBtn.setOnClickListener{  //비밀번호 길이
            result.text =  generator.generatepassword(10, passArray) //TODO : 글자 수 받아서 변환되게 하기
            SavePassword.visibility = View.VISIBLE
        }


        SavePassword.setOnClickListener {
            val intent = Intent(this, PasswordManage::class.java)
            intent.putExtra("password", result.text.toString())
            startActivity(intent)
        }
        */
    }
}