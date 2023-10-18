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
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
class WellcomeActivity : AppCompatActivity(), View.OnClickListener {

    // declaration Binding
    private lateinit var binding: ActivityWellcomeBinding
    // variabel menentukan animasi button
    private var isButtonLinkClicked = false
    // kode permintaan unik
    private val REQUEST_LOGIN = 123
    // Variable xml Wellcome
    private lateinit var btnGoogle: Button
    private lateinit var btnFacebook: Button
    private lateinit var textLinkRegister: TextView
    // GoogleLauncher dan FacebookLauncher
    private lateinit var googleLoginLauncher: ActivityResultLauncher<Intent>
    private lateinit var facebookLoginLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wellcome)
        // Binding
        binding = ActivityWellcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi xml
        btnGoogle = findViewById(R.id.btnLoginGoogle)
        btnFacebook = findViewById(R.id.btnLoginFacebook)
        textLinkRegister = findViewById(R.id.linkRegister)

        // Change form
        // Google
        btnGoogle.setOnClickListener(this)
        // facebook
        btnFacebook.setOnClickListener(this)
        // Register
        textLinkRegister.setOnClickListener(this)

        // inisalisasi GoogleLauncher dan FacebookLauncher
        googleLoginLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Handle the result of Google Login here
                val intent = Intent(this@WellcomeActivity, InterestActivity::class.java)
                startActivity(intent)
            }
        }
        facebookLoginLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Handle the result of Facebook Login here
                val intent = Intent(this@WellcomeActivity, InterestActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btnLoginGoogle -> {
                // Logika animasi button di klik
                if (!isButtonLinkClicked) {
                    btnGoogle.setBackgroundResource(R.drawable.glass_clicked)
                    isButtonLinkClicked = true
                }
                val googleLoginUrl = "https://accounts.google.com"
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(googleLoginUrl))
                googleLoginLauncher.launch(browserIntent)
            }
            R.id.btnLoginFacebook -> {
                // logika animasi button di click
                // Logika animasi button di klik
                if (!isButtonLinkClicked) {
                    btnGoogle.setBackgroundResource(R.drawable.glass_clicked)
                    isButtonLinkClicked = true
                }
                val googleLoginUrl = "https://accounts.google.com"
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(googleLoginUrl))
                googleLoginLauncher.launch(browserIntent)
            }
            R.id.linkRegister -> {
                // Intent
                val intent = Intent(this@WellcomeActivity, RegisterActivity::class.java)
                // logika animasi button di click
                if(!isButtonLinkClicked) {
                    textLinkRegister.setTextColor(ContextCompat.getColor(this, R.color.purple))
                    isButtonLinkClicked = true
                }
                startActivity(intent)
            }
        }
    }

    // aksi untuk button perangkat kembali di tekan
    override fun onResume() {
        super.onResume()
        // atur xml
        // google
        btnGoogle.setBackgroundResource(R.drawable.glass)
        // facebook
        btnFacebook.setBackgroundResource(R.drawable.glass)
        // linkRegister
        textLinkRegister.setTextColor(ContextCompat.getColor(this, R.color.black))

        // atur variable animasi
        isButtonLinkClicked = false
    }
}