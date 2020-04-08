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
    private val loginPreferences
        get() = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    private fun shortMsg(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginEdit = findViewById<EditText>(R.id.loginEditText)
        val passwordEdit = findViewById<EditText>(R.id.passwordEditText)
        val confirmButton = findViewById<Button>(R.id.confirmButton)
        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {
            val login = loginEdit.text.toString()
            val password = passwordEdit.text.toString()

            if (login.trim() != "" && password != "") {
                with(loginPreferences.edit()) {
                    putString(login, password)
                    commit()
                }
                shortMsg("OK. Now try to login")
            } else {
                shortMsg("Empty credentials not allowed")
            }
        }

        confirmButton.setOnClickListener {
            val login = loginEdit.text.toString()
            val password = passwordEdit.text.toString()

            val realPassword = loginPreferences.getString(login, null)
            if (realPassword != null) {
                if (password == realPassword) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra(KEY_LOGIN, login)
                    startActivity(intent)
                } else {
                    shortMsg("Wrong password")
                }
            } else {
                shortMsg("Wrong login")
            }
        }
    }

    companion object {
        const val PREF_NAME = "Accounts"
        const val KEY_LOGIN = "login"
    }
}
