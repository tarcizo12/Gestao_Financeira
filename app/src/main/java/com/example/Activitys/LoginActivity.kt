package com.example.Activitys

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import java.util.*


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mRegisterButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        mRegisterButton = findViewById(R.id.registerButton)
        mRegisterButton.setOnClickListener(this)

    }

    override fun onClick(button: View?) {
        val buttonPressed = button?.id

        when(buttonPressed){
            R.id.registerButton -> openActivity("Register")
        }


    }

    private fun openActivity(activitySelected: String) {
        var it: Intent?

        when(activitySelected){
            "Register" -> it = Intent(this,RegisterActivity::class.java)
            else -> it = null
        }

        startActivity(it)
    }
}




