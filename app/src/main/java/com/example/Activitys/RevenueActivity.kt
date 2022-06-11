package com.example.Activitys

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

class RevenueActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var revenueProfilePicture: ImageView
    private lateinit var revenueWelcome: TextView
    private lateinit var revenueRealizarReceita: TextView
    private lateinit var revenueValorEmReal: TextView
    private lateinit var revenueTituloDaReceita: TextView
    private lateinit var revenueDigiteOValor: EditText
    private lateinit var revenueDigiteOTitulo: EditText
    private lateinit var revenueRealizarBotao: Button
    private lateinit var dbInstance: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_revenue)

        revenueDigiteOValor = findViewById(R.id.revenueDigiteOValor)
        revenueDigiteOTitulo = findViewById(R.id.expenseDigiteOTitulo)
        revenueRealizarBotao = findViewById(R.id.revenueRealizarBotao)

        revenueDigiteOValor.setRawInputType(Configuration.KEYBOARD_12KEY)
        revenueRealizarBotao.setOnClickListener(this)

        dbInstance = FirebaseDatabase.getInstance()
        auth = Firebase.auth
    }

    override fun onClick(p0: View?) {
        val value = this.revenueDigiteOValor.text.toString()
        val title = this.revenueDigiteOTitulo.text.toString()

        setRevenue(value, title)

    }

    private fun setRevenue(value: String,title: String) {
        val userRefs = dbInstance.getReference("users")
        userRefs
            .orderByChild("email").equalTo(auth.currentUser?.email)
            .addChildEventListener(object: ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val userId = snapshot.key!!

                    val atualyUserMoney = snapshot.child("totalMoney").value.toString().toDouble()
                    val setNewMoneyUser =  atualyUserMoney + value.replace(',','.').toDouble()


                    val revenueRef = userRefs.child(userId).child("listRevenues")
                    val revenueId = revenueRef.push().key ?: ""

                    val revenue = Revenue(revenueId,title,value,true)

                        revenueRef.child(revenueId).setValue(revenue).addOnCompleteListener { task ->
                            if (task.isSuccessful){
                                userRefs.child(userId).child("totalMoney").setValue(setNewMoneyUser)
                                Toast.makeText(baseContext, "Receita cadastrada com sucesso!!", Toast.LENGTH_SHORT).show()
                                revenueDigiteOTitulo.text.clear()
                                revenueDigiteOValor.text.clear()
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

