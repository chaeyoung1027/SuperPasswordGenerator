package com.example.superpasswordgenerator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

data class password(var pw : String?) {}

class PasswordSave  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_save)
    }
}