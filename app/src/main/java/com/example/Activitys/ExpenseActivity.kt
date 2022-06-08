package com.example.Activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class ExpenseActivity : AppCompatActivity() {

    private lateinit var expenseProfilePicture: ImageView
    private lateinit var expenseWelcome: TextView
    private lateinit var expenseRealizarDespesa: TextView
    private lateinit var expenseValorEmReal: TextView
    private lateinit var expenseDigiteOValor: EditText
    private lateinit var expenseTituloDaReceita: TextView
    private lateinit var expenseDigiteOTitulo: EditText
    private lateinit var expenseRealizarBotao: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense)

        expenseProfilePicture = findViewById(R.id.expenseProfilePicture)
        expenseWelcome = findViewById(R.id.expenseWelcome)
        expenseRealizarDespesa = findViewById(R.id.expenseRealizarDespesa)
        expenseValorEmReal = findViewById(R.id.expenseValorEmReal)
        expenseDigiteOValor = findViewById(R.id.expenseDigiteOValor)
        expenseTituloDaReceita = findViewById(R.id.expenseTituloDaDespesa)
        expenseDigiteOTitulo = findViewById(R.id.revenueDigiteOTitulo)
        expenseRealizarBotao = findViewById(R.id.expenseRealizarBotao)
    }
}