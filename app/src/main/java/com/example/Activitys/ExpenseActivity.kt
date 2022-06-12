package com.example.Activitys

import Entitys.Expense
import Entitys.Revenue
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class ExpenseActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var expenseProfilePicture: ImageView
    private lateinit var expenseWelcome: TextView
    private lateinit var expenseRealizarDespesa: TextView
    private lateinit var expenseValorEmReal: TextView
    private lateinit var expenseTituloDaReceita: TextView
    private lateinit var expenseDigiteOValor: EditText
    private lateinit var expenseDigiteOTitulo: EditText
    private lateinit var expenseRealizarBotao: Button
    private lateinit var dbInstance: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense)

        expenseDigiteOValor = findViewById(R.id.expenseDigiteOValor)
        expenseDigiteOTitulo = findViewById(R.id.expenseDigiteOTitulo)
        expenseRealizarBotao = findViewById(R.id.expenseRealizarBotao)

        expenseDigiteOValor.setRawInputType(Configuration.KEYBOARD_12KEY)
        expenseRealizarBotao.setOnClickListener(this)

        dbInstance = FirebaseDatabase.getInstance()
        auth = Firebase.auth
    }

    override fun onClick(p0: View?) {
        val value = this.expenseDigiteOValor.text.toString()
        val title = this.expenseDigiteOTitulo.text.toString()

        setRevenue(value, title)
    }

    private fun setRevenue(value: String, title: String) {
        val userRefs = dbInstance.getReference("users")
        userRefs
            .orderByChild("email").equalTo(auth.currentUser?.email)
            .addChildEventListener(object: ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val userId = snapshot.key!!

                    val atualyUserMoney = snapshot.child("totalMoney").value.toString().toDouble()
                    val setNewMoneyUser =  atualyUserMoney - value.replace(',','.').toDouble()

                    val expenseRef = userRefs.child(userId).child("listExpenses")
                    val expenseId = expenseRef.push().key ?: ""
                    val expense = Expense(expenseId,title,value,false)

                    expenseRef.child(expenseId).setValue(expense).addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            userRefs.child(userId).child("totalMoney").setValue(setNewMoneyUser)
                            Toast.makeText(baseContext, "Despesa cadastrada com sucesso!!", Toast.LENGTH_SHORT).show()
                            expenseDigiteOTitulo.text.clear()
                            expenseDigiteOValor.text.clear()
                        }
                    }

                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

                override fun onChildRemoved(snapshot: DataSnapshot) {}

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

                override fun onCancelled(error: DatabaseError) {}
            }
            )
    }
    }
