package com.learning.helloworld

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit

class LoginActivity : AppCompatActivity() {

    var realLogin : String = ""
    var realPassword : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginEdit = findViewById<EditText>(R.id.loginEditText)
        val passwordEdit = findViewById<EditText>(R.id.passwordEditText)
        val confirmButton = findViewById<Button>(R.id.confirmButton)
        val registerButton = findViewById<Button>(R.id.registerButton)

        val preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        realLogin = preferences.getString(KEY_LOGIN, "")!!
        realPassword = preferences.getString(KEY_PASSWORD, "")!!

        registerButton.setOnClickListener {
            val login = loginEdit.text.toString()
            val password = passwordEdit.text.toString()
            val preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString(KEY_LOGIN, login)
            editor.putString(KEY_PASSWORD, password)
            editor.apply()
        }

        confirmButton.setOnClickListener {
            val login = loginEdit.text.toString()
            val password = passwordEdit.text.toString()

            if (login == realLogin) {
                if (password == realPassword) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra(KEY_LOGIN, login)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Wrong password", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Wrong login", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val PREF_NAME = "Preferences"
        const val KEY_LOGIN = "login"
        const val KEY_PASSWORD = "password"
    }
}
