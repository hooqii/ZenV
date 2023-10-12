package com.example.zenv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.zenv.databinding.ActivityWellcomeBinding

class WellcomeActivity : AppCompatActivity(), View.OnClickListener {

    // declaration Binding
    private lateinit var binding: ActivityWellcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wellcome)

        // Binding
        binding = ActivityWellcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Change form

        // Google
        val btnGoogle: Button = findViewById(R.id.btnLoginGoogle)
        btnGoogle.setOnClickListener(this)

        // facebook
        val btnFacebook: Button = findViewById(R.id.btnLoginFacebook)
        btnFacebook.setOnClickListener(this)

        // Register
        val textLinkRegister: TextView = findViewById(R.id.linkRegister)
        textLinkRegister.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btnLoginGoogle -> {
                val intent = Intent(this@WellcomeActivity, InterestActivity::class.java)
                startActivity(intent)
            }
            R.id.btnLoginFacebook -> {
                val intent = Intent(this@WellcomeActivity, InterestActivity::class.java)
                startActivity(intent)
            }
            R.id.linkRegister -> {
                val intent = Intent(this@WellcomeActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }
}