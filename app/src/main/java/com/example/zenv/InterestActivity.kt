package com.example.zenv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat

class InterestActivity : AppCompatActivity(), View.OnClickListener {
    // Varible global untuk file Interest
    // Daftar ID tombol
    private val buttonIds = listOf(
        R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5,
        R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button10
    )
    // Daftar tombol
    private val buttons = ArrayList<Button>()
    // variabel menentukan animasi button
    private var isButtonLinkClicked = false
    // Variabel xml khusus untuk interest
    private lateinit var btnBack: Button
    private lateinit var btnSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interest)

        // Inisialisasi tombol
        for (buttonId in buttonIds) {
            // ambil listOf
            val button = findViewById<Button>(buttonId)
            // Menambahkan OnClickListener untuk setiap tombol
            button.setOnClickListener { toggleButtonBackground(button) }
            // Menyimpan tombol dalam daftar untuk referensi lebih lanjut
            buttons.add(button)
        }

        // Inisialisasi Xml
        btnBack = findViewById(R.id.btnBack)
        btnSimpan = findViewById(R.id.btnSimpan)

        // Change form
        // tombol kembali(logo)
        val backWelcome: TextView = findViewById(R.id.btnBack)
        backWelcome.setOnClickListener(this)
        // tombol simpan
        val btnSimpan: Button = findViewById(R.id.btnSimpan)
        btnSimpan.setOnClickListener(this)

        // Tombol kembali perangkat android
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Pindah ke WelcomeActivity
                val intent = Intent(this@InterestActivity, WellcomeActivity::class.java)
                startActivity(intent)
            }
        }

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    // Fungsi untuk mengganti latar belakang tombol
    private fun toggleButtonBackground(button: Button) {
        if (button.isSelected) {
            // Ganti warna saat pertama kali diklik
            button.setBackgroundResource(R.drawable.btn_theme_2)
            button.setTextColor(ContextCompat.getColor(this, R.color.white))
        } else {
            // Ganti warna saat diklik lagi
            button.setBackgroundResource(R.drawable.btn_theme)
            button.setTextColor(ContextCompat.getColor(this, R.color.black))
        }
        // Mengubah status isSelected tombol
        button.isSelected = !button.isSelected
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnBack -> {
                // Navigasi kembali ke WellcomeActivity
                val intent = Intent(this@InterestActivity, WellcomeActivity::class.java)
                if(!isButtonLinkClicked) {
                    btnBack.setBackgroundResource(R.drawable.backarrow_clicked)
                    isButtonLinkClicked = true
                }
                startActivity(intent)
            }
            R.id.btnSimpan -> {
                // Navigasi ke MainActivity
                val intent = Intent(this@InterestActivity, MainActivity::class.java)
                // logika animasi button di click
                if(!isButtonLinkClicked) {
                    btnSimpan.setBackgroundResource(R.drawable.btn_theme_3)
                    isButtonLinkClicked = true
                }
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // ubah kembali
        btnSimpan.setBackgroundResource(R.drawable.btn_theme_2)
        btnBack.setBackgroundResource(R.drawable.backarrow)
        // jadikan valse
        isButtonLinkClicked = false
    }
}
