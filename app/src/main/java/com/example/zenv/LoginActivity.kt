package com.example.zenv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.zenv.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    // declaration binding
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Change form

        // link register
        val textLinkRegister: TextView = findViewById(R.id.linkRegister)
        textLinkRegister.setOnClickListener(this)

        // Back welcome
        val backWelcome: TextView = findViewById(R.id.btnBack)
        backWelcome.setOnClickListener(this)

        // Button Login
        val btLogin: TextView = findViewById(R.id.btnLogin)
        btLogin.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.linkRegister -> {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
            R.id.btnBack -> {
                val intent = Intent(this@LoginActivity, WellcomeActivity::class.java)
                startActivity(intent)
            }
            R.id.btnLogin -> {
                val intent = Intent(this@LoginActivity, InterestActivity::class.java)
                startActivity(intent)
            }
        }
    }
}