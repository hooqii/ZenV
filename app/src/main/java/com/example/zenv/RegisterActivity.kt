package com.example.zenv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.zenv.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    // declaration binding
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Binding
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Change form

        // link login
        val textLinkLogin: TextView = findViewById(R.id.linkLogin)
        textLinkLogin.setOnClickListener(this)

        // Button Register
        val btnRegister: TextView = findViewById(R.id.btnRegister)
        btnRegister.setOnClickListener(this)

        // Back Welcome
        val backWelcome: TextView = findViewById(R.id.btnBack)
        backWelcome.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.linkLogin -> {
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.btnRegister -> {
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.btnBack -> {
                val intent = Intent(this@RegisterActivity, WellcomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}