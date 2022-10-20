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
import kotlin.random.Random.Default.nextInt

private lateinit var database: DatabaseReference

class PasswordGenerator {

    //PassArray 배열 만들기
    fun feed(ingredient: List<String>) :ArrayList<String>{
        val passArray = ArrayList<String>()
        passArray.addAll(ingredient)

        return passArray
    }

    //비밀번호 길이 랜덤으로 설정
    fun length(Max:Int, Min:Int):Int{
        val Max= Max
        val Min= Min

        return (Min..Max).random()
    }

    fun generatepassword(length: Int, passArray:List<String>): String {    //랜덤 문자열 생성 함수
        val char = mutableListOf<String>()
        if(passArray==null){
            return ""
        }
        for(i in 1..length) {
            val randomNumber = passArray.random()
            char.add(randomNumber)
        }
        return char.joinToString("")
    }
}

class PasswordGenerate  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_generate)

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
        var passArray = ArrayList<String>()

        val generator = PasswordGenerator()
        generator.feed(Upperlist)

        //영어 추가
        var mode = "all"
        rg.setOnCheckedChangeListener { group, checkedId -> //영어 포함 Radio 버튼 값을 받기
            when(checkedId){
                R.id.all -> {
                    mode = "all"
                    generator.feed(Upperlist)
                    generator.feed(Lowerlist)
                }
                R.id.upper ->{
                    mode = "upper"
                    generator.feed(Upperlist)
                }
                R.id.lower ->{
                    mode = "lower"
                    generator.feed(Lowerlist)
                }

            }
        }

        //숫자추가
        if (NumberCheck.isChecked) generator.feed(Number)

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

    }
}