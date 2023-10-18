package com.example.zenv

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class DetailActivity : AppCompatActivity() {
    // Deklarasi variabel
    private lateinit var jumlahEditText: EditText
    private lateinit var buttonTambah: Button
    private lateinit var buttonKurang: Button
    private lateinit var shared: Button
    private lateinit var InstagramShared: Button
    private lateinit var bookmarkButton: Button
    private lateinit var eventButton: Button
    private lateinit var mainLayout: View
    private var jumlah = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Inisialisasi xml
        jumlahEditText = findViewById(R.id.jumlah)
        buttonTambah = findViewById(R.id.buttonTambah)
        buttonKurang = findViewById(R.id.buttonKurang)
        shared = findViewById(R.id.shared)
        InstagramShared = findViewById(R.id.instagramShared)
        bookmarkButton = findViewById(R.id.bookmark)
        eventButton = findViewById(R.id.button)
        mainLayout = findViewById(R.id.mainLayout)

        // Atur teks awal pada EditText
        jumlahEditText.setText(jumlah.toString())

        // Atur input type agar hanya menerima angka
        jumlahEditText.inputType = InputType.TYPE_CLASS_NUMBER

        // Tambahkan DigitsKeyListener untuk membatasi karakter yang diterima hanya angka
        val digitsKeyListener = DigitsKeyListener.getInstance("0123456789")
        jumlahEditText.keyListener = digitsKeyListener

        // Temukan tombol "Tambah"
        buttonTambah.setOnClickListener {
            // Ambil nilai dari EditText
            val nilaiStr = jumlahEditText.text.toString()
            // Validasi untuk memastikan bahwa nilai adalah angka
            if (nilaiStr.isNotEmpty()) {
                // Konversi nilai ke Int dan tambahkan 1
                val nilai = nilaiStr.toInt()
                jumlahEditText.setText((nilai + 1).toString())
            }
        }

        // Temukan tombol "Kurang"
        buttonKurang.setOnClickListener {
            // Ambil nilai dari EditText
            val nilaiStr = jumlahEditText.text.toString()
            // Validasi untuk memastikan bahwa nilai adalah angka
            if (nilaiStr.isNotEmpty()) {
                // Konversi nilai ke Int
                val nilai = nilaiStr.toInt()
                // Kurangkan 1 dari nilai jika nilainya lebih besar dari 0
                if (nilai > 0) {
                    jumlahEditText.setText((nilai - 1).toString())
                }
            }
        }

        // Mendeteksi sentuhan di luar keyboard saat user klik EditText
        mainLayout.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // Cek apakah keyboard sedang terbuka
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                val view = currentFocus
                if (view != null && imm.isActive) {
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                    view.clearFocus()
                }
            }
            return@setOnTouchListener false
        }

        // Button shared
        shared.setOnClickListener {
            // Teks yang akan Anda salin ke aplikasi lain
            val sharedText = "https://www.ZenV.com/Seminar-AI-Digital/29-September-2023/15.00-WIB?utm_source=salinlink&utm_medium=share&utm_campaign=PDP-112314353-4210198497-181023-iNmYzC"

            // Buat Intent untuk mengirim teks ke aplikasi lain
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, sharedText)
            sendIntent.type = "text/plain"

            // Gunakan Intent.createChooser untuk menampilkan pilihan aplikasi
            val chooser = Intent.createChooser(sendIntent, "Bagikan dengan")

            // Munculkan dialog pemilihan aplikasi
            if (sendIntent.resolveActivity(packageManager) != null) {
                startActivity(chooser)
            }
        }

        // Button instagram
        InstagramShared.setOnClickListener {
            // Teks yang akan disalin ke Instagram
            val sharedText = "https://www.ZenV.com/Seminar-AI-Digital/29-September-2023/15.00-WIB?utm_source=salinlink&utm_medium=share&utm_campaign=PDP-112314353-4210198497-181023-iNmYzC"

            // Buat Intent untuk mengirim teks ke aplikasi Instagram
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, sharedText)
            intent.`package` = "com.instagram.android"  // Package name untuk Instagram

            try {
                startActivity(intent)
            } catch (e: Exception) {
                // Instagram tidak diinstal, buka tautan di browser
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com"))
                startActivity(browserIntent)
            }
        }

        // Toast bookmark
        bookmarkButton.setOnClickListener {
            val eventName = eventButton.text.toString()
            val message = "$eventName telah ditambahkan"
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        }
    }
}