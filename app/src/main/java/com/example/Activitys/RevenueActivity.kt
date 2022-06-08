package com.example.Activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class RevenueActivity : AppCompatActivity() {

    private lateinit var revenueProfilePicture:ImageView
    private lateinit var revenueWelcome:TextView
    private lateinit var revenueRealizarReceita:TextView
    private lateinit var revenueValorEmReal:TextView
    private lateinit var revenueDigiteOValor:EditText
    private lateinit var revenueTituloDaReceita:TextView
    private lateinit var revenueDigiteOTitulo: EditText
    private lateinit var revenueRealizarBotao: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_revenue)

        revenueProfilePicture = findViewById(R.id.revenueProfilePicture)
        revenueWelcome= findViewById(R.id.revenueWelcome)
        revenueRealizarReceita = findViewById(R.id.revenueRealizarReceitaTitulo)
        revenueValorEmReal = findViewById(R.id.revenueValorEmReal)
        revenueDigiteOValor = findViewById(R.id.revenueDigiteOValor)
        revenueTituloDaReceita = findViewById(R.id.revenueTituloDaReceita)
        revenueDigiteOTitulo = findViewById(R.id.revenueDigiteOTitulo)
        revenueRealizarBotao = findViewById(R.id.revenueRealizarBotao)
    }
}