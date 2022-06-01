package com.example.Activitys


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mRegisterButton: Button
    private lateinit var mLoginButton: Button
    private lateinit var mUserEmail: EditText
    private lateinit var mUserPassword: EditText
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        mRegisterButton = findViewById(R.id.registerButton)
        mRegisterButton.setOnClickListener(this)

        mUserEmail = findViewById(R.id.loginEmail)
        mUserPassword = findViewById(R.id.loginPassword)

        mLoginButton = findViewById(R.id.loginButton)
        mLoginButton.setOnClickListener(this)

        auth = Firebase.auth


    }

    override fun onClick(button: View?) {

        when (button?.id) {
            R.id.registerButton -> startActivity(Intent(this, RegisterActivity::class.java))
            R.id.loginButton -> loginUser()
        }

    }


    private fun loginUser() {
        val email = mUserEmail.text.trim().toString()
        val password = mUserPassword.text.trim().toString()

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                startActivity(Intent(this, UserActivity::class.java))
                Toast.makeText(baseContext, "Seja bem vindo ", Toast.LENGTH_SHORT).show()
                finish()
            } else {
            }
        }

    }
}




