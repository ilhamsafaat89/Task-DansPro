package com.daurulang.jobinformation

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsername: EditText = findViewById(R.id.etUsername)
        val etPassword: EditText = findViewById(R.id.etPassword)
        val btnLogin: Button = findViewById<Button>(R.id.btnLogin)


        btnLogin.setOnClickListener{
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (isValidCredentials(username, password)){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                showErrorMessageDialog("Invalid Credentials", "Please check your username and password")
            }
        }

    }

    private fun showErrorMessageDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") {dialoginterface: DialogInterface, _: Int ->
                dialoginterface.dismiss()
            }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        val validUsername = "admin"
        val validPassword = "admin123"

        return username == validUsername && password == validPassword
    }


}