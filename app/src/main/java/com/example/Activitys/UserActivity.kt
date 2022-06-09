package com.example.Activitys

import android.content.Intent
import android.icu.number.Precision
import android.os.Bundle
import android.util.Log

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.lang.Math.round


class UserActivity : AppCompatActivity(), View.OnClickListener {
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
    private lateinit var dbInstance: FirebaseDatabase
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
        userButtonRegisterRecipe.setOnClickListener(this)

        userButtonRegisterExpense = findViewById(R.id.userButtonRegisterExpense)
        userButtonRegisterExpense.setOnClickListener(this)


        userButtonRecipesAndExpenses = findViewById(R.id.userButtonRecipesAndExpenses)
        userButtonRecipesAndExpenses.setOnClickListener(this)

        userTextRegisterRecipe = findViewById(R.id.userTextRegisterRecipe)
        userTextRegisterExpense = findViewById(R.id.userButtonRegisterExpense)
        userTextDispensesAndExpenses = findViewById(R.id.userButtonRecipesAndExpenses)


        auth = Firebase.auth
        dbInstance = FirebaseDatabase.getInstance()


    }

    override fun onStart() {
        super.onStart()

        if (auth.currentUser != null) {
            val userRefs = dbInstance.getReference("users")
            userRefs
                .orderByChild("email").equalTo(auth.currentUser?.email)
                .addChildEventListener(object: ChildEventListener{
                    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

                        val setNameUser = snapshot.child("name").value.toString()
                        var setMoneyUser = snapshot.child("totalMoney").value.toString().toDouble()
                        val formattedNumber = String.format("%.2f", setMoneyUser)

                        userWelcome.text =  "Seja bem vindo $setNameUser"
                        userMoney.text =  "R$ $formattedNumber"

                    }

                    override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

                    override fun onChildRemoved(snapshot: DataSnapshot) {}

                    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

                    override fun onCancelled(error: DatabaseError) {}
                })
        } else {

        }

    }

    override fun onClick(button: View?) {
        when(button?.id){
            R.id.userButtonRegisterRecipe -> startActivity(Intent(this, RevenueActivity::class.java))
            R.id.userButtonRegisterExpense -> startActivity(Intent(this,ExpenseActivity::class.java))
         // R.id.userButtonRecipesAndExpenses -> startActivity(Intent(this, ::class.java))
        }
    }


}