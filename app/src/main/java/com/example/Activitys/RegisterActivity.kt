package com.example.Activitys
import Entitys.Expense
import Entitys.Revenue
import Entitys.User
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var registerName: EditText
    private lateinit var registerEmail: EditText
    private lateinit var registerMobileNumber: EditText
    private lateinit var registerPassword: EditText
    private lateinit var registerConfirmPassword: EditText
    private lateinit var registerButtonConfirm: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        registerName = findViewById(R.id.registerName)
        registerEmail = findViewById(R.id.registerEmail)
        registerMobileNumber = findViewById(R.id.registerMobileNumber)
        registerPassword = findViewById(R.id.registerPassword)
        registerConfirmPassword = findViewById(R.id.registerConfirmPassword)
        registerButtonConfirm = findViewById(R.id.registerButtonConfirm)


        auth = Firebase.auth
        db = FirebaseDatabase.getInstance()

    }

    override fun onResume() {
        super.onResume()
        registerButtonConfirm.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val email = registerEmail.text.toString().trim()
        val name = registerName.text.toString().trim()
        val number = registerMobileNumber.text.toString().trim()
        val password = registerPassword.text.toString().trim()
        val confirmPassword = registerConfirmPassword.text.toString().trim()

        val validate = validateData(email, name, number, password, confirmPassword)
        if(validate){ registerUser(email, password) }

    }

    private fun registerUser(email: String,password: String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    saveDataUsers()
                    Intent(this,LoginActivity::class.java)
                    Toast.makeText(baseContext, "Registro feito com sucesso", Toast.LENGTH_SHORT).show()
                    finish()

                } else {
                    registerEmail.error = "Email já cadastrado como usuário"
                }
            }

    }

    private fun saveDataUsers(){
        val email = registerEmail.text.toString().trim()
        val name = registerName.text.toString().trim()
        val number = registerMobileNumber.text.toString().trim()
        val dbRef = db.getReference("users")
        val key = dbRef.push().key ?: ""
        val user = User(email,number,name)

        dbRef.child(key).setValue(user)

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