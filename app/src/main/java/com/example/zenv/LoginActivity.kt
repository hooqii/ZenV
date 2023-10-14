package com.example.zenv

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.zenv.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    // declaration binding
    private lateinit var binding: ActivityLoginBinding

    // variabel menentukan animasi button
    private var isButtonLinkClicked = false

    // Variabel menentukan animasi show and hide password
    private var isPasswordVisible = false

    // Variabel menentukan efek glass frame
    private lateinit var frameLayout: FrameLayout
    private var isFrameLayoutClicked = false

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

        // Mendeteksi sentuhan di luar keyboard saat user click edit text
        val rootView: View = findViewById(android.R.id.content)
        rootView.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // Cek apakah keyboard sedang terbuka
                val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                val view = currentFocus
                if (view != null && imm.isActive) {
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                    view.clearFocus() // Untuk menghilangkan fokus dari EditText yang di-klik
                }
            }
            return@setOnTouchListener false
        }

        // deklarasi variable ke id xml
        val etPassword: EditText = findViewById(R.id.etPassword)
        val ivShowPassword: ImageView = findViewById(R.id.ivShowPassword)

        // logika click menekan icon show and hide password
        ivShowPassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            val transformationMethod = if (isPasswordVisible) {
                null
            } else {
                PasswordTransformationMethod.getInstance()
            }
            etPassword.transformationMethod = transformationMethod

            if (isPasswordVisible) {
                ivShowPassword.setImageResource(R.drawable.icon_eye_on)
            } else {
                ivShowPassword.setImageResource(R.drawable.icon_eye_off)
            }
        }

        // inisialisasi frameLayout
        frameLayout = findViewById(R.id.frameLayout)

        // Logic clicked frameLayout
        frameLayout.setOnClickListener {
            if (!isFrameLayoutClicked) {
                // Cek apakah keyboard sedang terbuka
                val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                val view = currentFocus
                if (view != null && imm.isActive) {
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                    view.clearFocus()
                }

                frameLayout.setBackgroundResource(R.drawable.card_clicked)
                isFrameLayoutClicked = true

                // Setel tunda 0.5 detik untuk kembali ke keadaan normal
                Handler(Looper.getMainLooper()).postDelayed({
                    frameLayout.setBackgroundResource(R.drawable.card)
                    isFrameLayoutClicked = false
                }, 300)
            }
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btnBack -> {
                val intent = Intent(this@LoginActivity, WellcomeActivity::class.java)
                startActivity(intent)
            }
            R.id.btnLogin -> {
                val intent = Intent(this@LoginActivity, InterestActivity::class.java)
                // variabel pemanggilan button
                var myButton = findViewById<Button>(R.id.btnLogin)
                if(!isButtonLinkClicked) {
                    myButton.setBackgroundResource(R.drawable.btn_theme_3)
                    isButtonLinkClicked = true
                }
                startActivity(intent)
            }
            R.id.linkRegister -> {
                // Intent
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                var myLinkText = findViewById<TextView>(R.id.linkRegister)
                // logika animasi button di click
                if(!isButtonLinkClicked) {
                    myLinkText.setTextColor(ContextCompat.getColor(this, R.color.purple))
                    isButtonLinkClicked = true
                }
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // btnLogin
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val linkRegister = findViewById<TextView>(R.id.linkRegister)

        // atur xml
        // btnLogin
        btnLogin.setBackgroundResource(R.drawable.btn_theme_2)
        // linkRegister
        linkRegister.setTextColor(ContextCompat.getColor(this, R.color.black))

        // atur variable animasi
        isButtonLinkClicked = false
    }
}