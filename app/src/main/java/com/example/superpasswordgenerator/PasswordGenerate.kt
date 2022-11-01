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
    fun pass_length(Max:Int, Min:Int):Int{
        val Max= Max
        val Min= Min

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_generate)

        generator.feed(PasswordGenerator.ALPHABET_UPPERCASE)
        generator.feed(PasswordGenerator.ALPHABET_LOWERCASE)
        generator.feed(PasswordGenerator.DIGITS)

        val MaxV = findViewById<Slider>(R.id.max_v)                     //비밀번호 길이(Max)
        val MinV = findViewById<Slider>(R.id.min_v)                     //비밀번호 길이(Min)
        val result = findViewById<TextView>(R.id.result)                //결과를 보여주는 칸
        val GenBtn = findViewById<Button>(R.id.password_generate_Btn)   //생성하기 버튼
        val SavePassword = findViewById<Button>(R.id.password_save_Btn) //저장하기 버튼
        val SelectAll = findViewById<TextView>(R.id.select_all)         //특수문자 전체 선택

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
            result.text = generator.generate(generator.pass_length(MaxV.value.toInt(), MinV.value.toInt()))
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



        /*
        var NumberCheck = findViewById<CheckBox>(R.id.numbercheck)
        val rg = findViewById<RadioGroup>(R.id.rg)
        var SpecChar = listOf("!", "@", "#", "$","^","&","*")
        var passArray = ArrayList<String>()

        val generator = PasswordGenerator()


        //문자추가





        for (i in 1 until spec.childCount) {
            if((spec.getChildAt(i) as CheckBox).isChecked)
                passArray.add(SpecChar[i])
        }
        */

        SavePassword.setOnClickListener {
            val intent = Intent(this, PasswordManage::class.java)
            intent.putExtra("password", result.text.toString())
            startActivity(intent)
        }
    }
}