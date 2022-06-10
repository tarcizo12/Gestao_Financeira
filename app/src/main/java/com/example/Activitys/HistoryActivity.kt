package com.example.Activitys

import Entitys.HistoryData
import Entitys.User
import adapter.HistoryAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class HistoryActivity : AppCompatActivity() {

    private lateinit var historyList:RecyclerView
    private lateinit var db: FirebaseDatabase
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        db = FirebaseDatabase.getInstance()
        auth = Firebase.auth


        historyList = findViewById(R.id.historyList)
        historyList.layoutManager = LinearLayoutManager(this) //listagem linear

    }

    override fun onStart() {
        super.onStart()
        createDatabase()
    }


    fun createDatabase() {
        val findAll = mutableListOf<HistoryData>()
        val userRef = this.db.getReference("users")
        userRef
            .orderByChild("email")
            .equalTo(auth.currentUser?.email)
            .addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(children in snapshot.children){
                        val user = children.getValue(User::class.java)
                        val listOfRevenues = user?.listRevenues?.values?.toList()
                        val listOfExpense = user?.listExpenses?.values?.toList()

                        listOfRevenues!!.forEach {
                            findAll.add(HistoryData(it.id,it.name,it.value,it.typeValue))
                        }
                        listOfExpense!!.forEach {
                            findAll.add(HistoryData(it.id,it.name,it.value,it.typeValue))
                        }

                        historyList.adapter = HistoryAdapter(findAll)
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })


    }
}