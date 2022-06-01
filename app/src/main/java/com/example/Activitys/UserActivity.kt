package com.example.Activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class UserActivity : AppCompatActivity() {
    private lateinit var userView: View
    private lateinit var userProfilePicture: ImageView

    private lateinit var userWelcome: TextView
    private lateinit var userBalance: TextView
    private lateinit var userMoney: TextView

    private lateinit var userButtonRegisterRecipe: Button
    private lateinit var userButtonRegisterExpense: Button
    private lateinit var userButtonRecipesAndExpenses: Button

    private lateinit var userTextRegisterRecipe: TextView
    private lateinit var userTextRegisterExpense: TextView
    private lateinit var userTextDispensesAndExpenses: TextView
    private lateinit var userLoged: Firebase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_activity)

        userView = findViewById(R.id.userView)
        userProfilePicture = findViewById(R.id.userProfilePicture)

        userWelcome = findViewById(R.id.userWelcome)
        userBalance = findViewById(R.id.userBalance)
        userMoney = findViewById(R.id.userMoney)

        userButtonRegisterRecipe = findViewById(R.id.userButtonRegisterRecipe)
        userButtonRegisterExpense = findViewById(R.id.userButtonRegisterExpense)
        userButtonRecipesAndExpenses = findViewById(R.id.userButtonRecipesAndExpenses)

        userTextRegisterRecipe = findViewById(R.id.userTextRegisterRecipe)
        userTextRegisterExpense = findViewById(R.id.userButtonRegisterExpense)
        userTextDispensesAndExpenses = findViewById(R.id.userButtonRecipesAndExpenses)


        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()

        if (auth.currentUser != null) {
            val userId = auth.currentUser!!.email.toString().split("@")[0]
            val db = FirebaseDatabase.getInstance().getReference("users")
                .child(userId)
                .child("name").get()
                .addOnSuccessListener {
                    userWelcome.text = "Seja bem vindo ${it.value}"
                }

        } else {

        }

    }
}