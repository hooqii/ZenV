package com.example.zenv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat

class InterestActivity : AppCompatActivity(), View.OnClickListener {

    //declartion button widget
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button
    private lateinit var button10: Button

    // Variable untuk melacak status tombol
    private var button1Clicked = false
    private var button2Clicked = false
    private var button3Clicked = false
    private var button4Clicked = false
    private var button5Clicked = false
    private var button6Clicked = false
    private var button7Clicked = false
    private var button8Clicked = false
    private var button9Clicked = false
    private var button10Clicked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interest)

        // inisialitation buton
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)
        button10 = findViewById(R.id.button10)

        // Set onClickListener untuk setiap tombol
        button1.setOnClickListener { toggleButtonBackground(button1, button1Clicked) }
        button2.setOnClickListener { toggleButtonBackground(button2, button2Clicked) }
        button3.setOnClickListener { toggleButtonBackground(button3, button3Clicked) }
        button4.setOnClickListener { toggleButtonBackground(button4, button4Clicked) }
        button5.setOnClickListener { toggleButtonBackground(button5, button5Clicked) }
        button6.setOnClickListener { toggleButtonBackground(button6, button6Clicked) }
        button7.setOnClickListener { toggleButtonBackground(button7, button7Clicked) }
        button8.setOnClickListener { toggleButtonBackground(button8, button8Clicked) }
        button9.setOnClickListener { toggleButtonBackground(button9, button9Clicked) }
        button10.setOnClickListener { toggleButtonBackground(button10, button10Clicked) }

        // Change form

        // Back welcome
        val backWelcome: TextView = findViewById(R.id.btnBack)
        backWelcome.setOnClickListener(this)
    }

    private fun toggleButtonBackground(button: Button, clicked: Boolean) {
        if (!clicked) {
            // Ganti warna saat pertama kali diklik
            button.setBackgroundResource(R.drawable.clicked_btn_theme)
            button.setTextColor(ContextCompat.getColor(this, R.color.white))
        } else {
            // Ganti warna saat diklik lagi
            button.setBackgroundResource(R.drawable.btn_theme)
            button.setTextColor(ContextCompat.getColor(this, R.color.black))
        }

        // Perbarui status tombol
        when (button.id) {
            R.id.button1 -> button1Clicked = !button1Clicked
            R.id.button2 -> button2Clicked = !button2Clicked
            R.id.button3 -> button3Clicked = !button3Clicked
            R.id.button4 -> button4Clicked = !button4Clicked
            R.id.button5 -> button5Clicked = !button5Clicked
            R.id.button6 -> button6Clicked = !button6Clicked
            R.id.button7 -> button7Clicked = !button7Clicked
            R.id.button8 -> button8Clicked = !button8Clicked
            R.id.button9 -> button9Clicked = !button9Clicked
            R.id.button10 -> button10Clicked = !button10Clicked
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btnBack -> {
                val intent = Intent(this@InterestActivity, WellcomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}