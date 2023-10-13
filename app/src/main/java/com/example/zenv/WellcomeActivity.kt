package com.example.zenv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.zenv.databinding.ActivityWellcomeBinding

class WellcomeActivity : AppCompatActivity(), View.OnClickListener {

    // declaration Binding
    private lateinit var binding: ActivityWellcomeBinding

    // variabel menentukan animasi button
    private var isButtonClicked = false

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
        // variabel perpindaha halaman

        // facebook dan google
        val intent = Intent(this@WellcomeActivity, InterestActivity::class.java)
        // Register
        val intent2 = Intent(this@WellcomeActivity, RegisterActivity::class.java)

        when(v.id){
            R.id.btnLoginGoogle -> {
                // variabel pemanggilan button
                var myButton = findViewById<Button>(R.id.btnLoginGoogle)
                // logika animasi button di click
                if(!isButtonClicked) {
                    myButton.setBackgroundResource(R.drawable.clicked_glass)
                    isButtonClicked = true
                }
                startActivity(intent)
            }
            R.id.btnLoginFacebook -> {
                // variabel pemanggilan button
                var myButton = findViewById<Button>(R.id.btnLoginFacebook)
                // logika animasi button di click
                if(!isButtonClicked) {
                    myButton.setBackgroundResource(R.drawable.clicked_glass)
                    isButtonClicked = true
                }
                startActivity(intent)
            }
            R.id.linkRegister -> {
                // variabel pemanggilan button
                var myLinkText = findViewById<TextView>(R.id.linkRegister)
                // logika animasi button di click
                if(!isButtonClicked) {
                    myLinkText.setTextColor(ContextCompat.getColor(this, R.color.purple))
                    isButtonClicked = true
                }
                startActivity(intent2)
            }
        }
    }

    // aksi untuk button perangkat kembali di tekan
    override fun onResume() {
        super.onResume()

        // ganti tombol ke awal
        // google
        val myButton = findViewById<Button>(R.id.btnLoginGoogle)
        // facebook
        val myButton2 = findViewById<Button>(R.id.btnLoginFacebook)
        // linkRegister
        val myLink = findViewById<TextView>(R.id.linkRegister)

        // atur xml
        // google
        myButton.setBackgroundResource(R.drawable.glass)
        // facebook
        myButton2.setBackgroundResource(R.drawable.glass)
        // linkRegister
        myLink.setTextColor(ContextCompat.getColor(this, R.color.black))

        // atur variable animasi
        isButtonClicked = false
    }
}