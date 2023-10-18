package com.example.zenv

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.zenv.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    // declaration binding
    private lateinit var binding: ActivityRegisterBinding
    // variabel menentukan animasi button
    private var isButtonLinkClicked = false
    // Variabel menentukan animasi show and hide password
    private var isPasswordVisible = false
    // Variabel menentukan efek glass frame
    private lateinit var frameLayout: FrameLayout
    private var isFrameLayoutClicked = false
    // variabel xml Register
    private lateinit var linkLogin: TextView
    private lateinit var btnRegister: TextView
    private lateinit var btnBack: TextView
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var ivShowPassword: ImageView
    private lateinit var ivShowConfirmPassword: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Binding
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // inisialisasi xml
        linkLogin = findViewById(R.id.linkLogin)
        btnRegister = findViewById(R.id.btnRegister)
        btnBack = findViewById(R.id.btnBack)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        ivShowPassword = findViewById(R.id.ivShowPassword)
        ivShowConfirmPassword = findViewById(R.id.ivShowConfirmPassword)

        // Change form
        // link login
        linkLogin.setOnClickListener(this)
        // Button Register
        btnRegister.setOnClickListener(this)
        // Back Welcome
        btnBack.setOnClickListener(this)

        // mendeteksi sentuhan di luar keyboard akan menutup keyboard
        val rootView: View = findViewById(android.R.id.content)
        rootView.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // Cek apakah keyboard sedang terbuka
                val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                val view = currentFocus
                if (view != null && imm.isActive) {
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                    view.clearFocus()
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

        ivShowConfirmPassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            val transformationMethod = if (isPasswordVisible) {
                null
            } else {
                PasswordTransformationMethod.getInstance()
            }
            etConfirmPassword.transformationMethod = transformationMethod

            if (isPasswordVisible) {
                ivShowConfirmPassword.setImageResource(R.drawable.icon_eye_on)
            } else {
                ivShowConfirmPassword.setImageResource(R.drawable.icon_eye_off)
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
                // Intent
                val intent = Intent(this@RegisterActivity, WellcomeActivity::class.java)
                if(!isButtonLinkClicked) {
                    btnBack.setBackgroundResource(R.drawable.backarrow_clicked)
                    isButtonLinkClicked = true
                }
                startActivity(intent)
            }
            R.id.btnRegister -> {
                // intent
                val intent2 = Intent(this@RegisterActivity, LoginActivity::class.java)
                // Ganti warna
                if(!isButtonLinkClicked) {
                    btnRegister.setBackgroundResource(R.drawable.btn_theme_3)
                    isButtonLinkClicked = true
                }
                // alert konfirmasi
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setMessage("Apakah Anda yakin?")
                    .setPositiveButton("Ya") { _, _ ->
                        // Pindah ke LoginActivity
                        startActivity(intent2)
                    }
                    .setNegativeButton("Tidak") { dialog, _ ->
                        dialog.dismiss()
                    }

                val alertDialog = dialogBuilder.create()
                alertDialog.show()
            }
            R.id.linkLogin -> {
                // Intent
                val intent3 = Intent(this@RegisterActivity, LoginActivity::class.java)
                // logika animasi button di click
                if(!isButtonLinkClicked) {
                    linkLogin.setTextColor(ContextCompat.getColor(this, R.color.purple))
                    isButtonLinkClicked = true
                }
                startActivity(intent3)
            }
        }
    }

    // aksi untuk button perangkat kembali di tekan
    override fun onResume() {
        super.onResume()
        // Register
        btnRegister.setBackgroundResource(R.drawable.btn_theme_2)
        // linkLogin
        linkLogin.setTextColor(ContextCompat.getColor(this, R.color.black))
        // btnBack
        btnBack.setBackgroundResource(R.drawable.backarrow)

        // atur variable animasi
        isButtonLinkClicked = false
    }
}