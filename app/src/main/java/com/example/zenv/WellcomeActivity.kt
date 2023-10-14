package com.example.zenv

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.zenv.databinding.ActivityWellcomeBinding
class WellcomeActivity : AppCompatActivity(), View.OnClickListener {

    // declaration Binding
    private lateinit var binding: ActivityWellcomeBinding

    // variabel menentukan animasi button
    private var isButtonClicked = false

    // kode permintaan unik
    private val REQUEST_LOGIN = 123

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
                // variabel pemanggilan button
                var myButton = findViewById<Button>(R.id.btnLoginGoogle)
                // logika animasi button di click
                if(!isButtonClicked) {
                    myButton.setBackgroundResource(R.drawable.glass_clicked)
                    isButtonClicked = true
                }
                val googleLoginUrl = "https://accounts.google.com"
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(googleLoginUrl))
                try {
                    startActivityForResult(browserIntent, REQUEST_LOGIN)
                } catch (e: ActivityNotFoundException) {
                    // Handle the case where no browser app is available
                    // You can show a message to the user to install a browser
                }
            }
            R.id.btnLoginFacebook -> {
                // variabel pemanggilan button
                var myButton = findViewById<Button>(R.id.btnLoginFacebook)
                // logika animasi button di click
                if(!isButtonClicked) {
                    myButton.setBackgroundResource(R.drawable.glass_clicked)
                    isButtonClicked = true
                }
                val facebookLoginUrl = "https://www.facebook.com" // Gantilah dengan URL login Facebook yang sesuai
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(facebookLoginUrl))
                try {
                    startActivityForResult(browserIntent, REQUEST_LOGIN)
                } catch (e: ActivityNotFoundException) {
                    // Handle the case where no browser app is available
                    // Anda dapat menampilkan pesan kepada pengguna untuk menginstal peramban
                }
            }
            R.id.linkRegister -> {
                // Intent
                val intent = Intent(this@WellcomeActivity, RegisterActivity::class.java)
                // variabel pemanggilan button
                var myLinkText = findViewById<TextView>(R.id.linkRegister)
                // logika animasi button di click
                if(!isButtonClicked) {
                    myLinkText.setTextColor(ContextCompat.getColor(this, R.color.purple))
                    isButtonClicked = true
                }
                startActivity(intent)
            }
        }
    }

    // aksi untuk btnLoginGoogle pergi ke chrome
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_LOGIN && resultCode == Activity.RESULT_OK) {
            // Pengguna telah berhasil masuk melalui browser atau aplikasi lain
            // Sekarang arahkan mereka ke InterestActivity
            val intent = Intent(this@WellcomeActivity, InterestActivity::class.java)
            startActivity(intent)
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