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
import androidx.activity.OnBackPressedCallback
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
    private var isFrameLayoutClicked = false
    // Variabel xml untuk Login
    private lateinit var etPassword: EditText
    private lateinit var ivShowPassword: ImageView
    private lateinit var frameLayout: FrameLayout
    private lateinit var btnLogin: Button
    private lateinit var btnBack: Button
    private lateinit var linkRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi xml
        etPassword = findViewById(R.id.etPassword)
        ivShowPassword = findViewById(R.id.ivShowPassword)
        frameLayout = findViewById(R.id.frameLayout)
        btnLogin = findViewById(R.id.btnLogin)
        btnBack = findViewById(R.id.btnBack)
        linkRegister = findViewById(R.id.linkRegister)

        // Change form
        // link register
        linkRegister.setOnClickListener(this)
        // Back welcome
        btnBack.setOnClickListener(this)
        // Button Login
        btnLogin.setOnClickListener(this)

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

        // Tombol kembali perangkat android
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Pindah ke WelcomeActivity
                val intent = Intent(this@LoginActivity, WellcomeActivity::class.java)
                startActivity(intent)
            }
        }

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btnBack -> {
                // intent
                val intent = Intent(this@LoginActivity, WellcomeActivity::class.java)
                if(!isButtonLinkClicked) {
                    btnBack.setBackgroundResource(R.drawable.backarrow_clicked)
                    isButtonLinkClicked = true
                }
                startActivity(intent)
            }
            R.id.btnLogin -> {
                // intent
                val intent2 = Intent(this@LoginActivity, InterestActivity::class.java)
                if(!isButtonLinkClicked) {
                    btnLogin.setBackgroundResource(R.drawable.btn_theme_3)
                    isButtonLinkClicked = true
                }
                startActivity(intent2)
            }
            R.id.linkRegister -> {
                // intent
                val intent3 = Intent(this@LoginActivity, RegisterActivity::class.java)
                // logika animasi button di click
                if(!isButtonLinkClicked) {
                    linkRegister.setTextColor(ContextCompat.getColor(this, R.color.purple))
                    isButtonLinkClicked = true
                }
                startActivity(intent3)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // btnLogin
        btnLogin.setBackgroundResource(R.drawable.btn_theme_2)
        // linkRegister
        linkRegister.setTextColor(ContextCompat.getColor(this, R.color.black))
        // btnBack
        btnBack.setBackgroundResource(R.drawable.backarrow)

        // atur variable animasi
        isButtonLinkClicked = false
    }
}