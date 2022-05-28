package com.example.Activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlin.math.log

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var registerName: EditText
    private lateinit var registerEmail: EditText
    private lateinit var registerMobileNumber: EditText
    private lateinit var registerPassword: EditText
    private lateinit var registerConfirmPassword: EditText
    private lateinit var registerButtonConfirm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        registerName = findViewById(R.id.registerName)
        registerEmail = findViewById(R.id.registerEmail)
        registerMobileNumber = findViewById(R.id.registerMobileNumber)
        registerPassword = findViewById(R.id.registerPassword)
        registerConfirmPassword = findViewById(R.id.registerConfirmPassword)
        registerButtonConfirm = findViewById(R.id.registerButtonConfirm)

        registerButtonConfirm.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {

        val email = registerEmail.text.toString().trim()
        val name = registerName.text.toString().trim()
        val number = registerMobileNumber.text.toString().trim()
        val password = registerPassword.text.toString().trim()
        val confirmPassword = registerConfirmPassword.text.toString().trim()

        if(validateData(email, name, number, password, confirmPassword)) registerUser()

    }

    private fun registerUser() {
        TODO("Not yet implemented")
    }

    private fun validateData(
        email: String,
        name: String,
        number: String,
        password: String,
        confirmPassword: String
    ): Boolean {

        var confirmedData = true

        if (email.isEmpty()) {
            registerEmail.error = "Coloque um email válido"
            confirmedData = false
        }
        if (name.isEmpty()) {
            registerName.error = "Coloque um nome válido"
            confirmedData = false
        }
        if (number.isEmpty()) {
            registerMobileNumber.error = "Coloque um número válido"
            confirmedData = false
        }
        if (password.isEmpty()) {
            registerPassword.error = "Coloque uma senha válida"
            confirmedData = false
        }
        if (confirmPassword.isEmpty()) {
            registerConfirmPassword.error = "Coloque uma confirmação válida"
            confirmedData = false
        }
        if (password != confirmPassword) {
            registerConfirmPassword.error = "Os campos de senha devem ser iguais"
            confirmedData = false
        }

        return confirmedData

    }


}