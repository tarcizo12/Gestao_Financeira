package com.example.Activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerName: EditText
    private lateinit var registerEmail: EditText
    private lateinit var registerMobileNumber: EditText
    private lateinit var registerPassword: EditText
    private lateinit var registerConfirmaPassword: EditText
    private lateinit var registerRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        registerName = findViewById(R.id.registerName)
        registerEmail = findViewById(R.id.registerEmail)
        registerMobileNumber = findViewById(R.id.registerMobileNumber)
        registerPassword = findViewById(R.id.registerPassword)
        registerConfirmaPassword = findViewById(R.id.registerConfirmPassword)
        registerRegister = findViewById(R.id.registerRegister)
    }


}