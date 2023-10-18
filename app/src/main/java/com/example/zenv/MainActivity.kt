package com.example.zenv

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zenv.adapters.RvItem
import com.example.zenv.adapters.RvItem2
import com.example.zenv.databinding.ActivityMainBinding
import com.example.zenv.model.ItemModel
import com.example.zenv.model.ItemModel2

class MainActivity : AppCompatActivity() {
    // declaration binding
    private lateinit var binding: ActivityMainBinding
    // RecycleView 1
    private lateinit var adapterMain: RvItem
    // RecycleView 2
    private lateinit var adapterMain2: RvItem2
    // variable double clicked
    private var doubleBackToExitPressedOnce = false
    // variable untuk Toast
    private var message = "Tekan sekali lagi untuk keluar"
    // variable untuk mengetahui clicked tombol kembali
    private var numberClicked = 1
    // variable xml
    private lateinit var mainLayout: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setRvAdapter()

        // inisialisasi xml
        mainLayout = findViewById(R.id.mainLayout)

        // Button popular dan newest logic
        val btnPopular = binding.btnPopular
        val btnNewest = binding.btnNewest

        // atur latar belakang button
        btnPopular.setBackgroundResource(R.drawable.btn_theme_2)
        btnNewest.setBackgroundResource(R.color.no_color)
        // atur textColor
        btnPopular.setTextColor(ContextCompat.getColor(this, R.color.white))
        btnNewest.setTextColor(ContextCompat.getColor(this, R.color.dark_blue))

        // setOnClickListener tombol popular
        btnPopular.setOnClickListener{
            // atur latar belakang button
            btnPopular.setBackgroundResource(R.drawable.btn_theme_2)
            btnNewest.setBackgroundResource(R.color.no_color)
            // atur textColor
            btnPopular.setTextColor(ContextCompat.getColor(this, R.color.white))
            btnNewest.setTextColor(ContextCompat.getColor(this, R.color.dark_blue))
            // atur RecycleView
            binding.frameLayoutCard.visibility = View.VISIBLE
            binding.frameLayoutCard2.visibility = View.GONE
        }

        // setOnClickListener tombol newest
        btnNewest.setOnClickListener{
            // atur latar belakang button
            btnPopular.setBackgroundResource(R.color.no_color)
            btnNewest.setBackgroundResource(R.drawable.btn_theme_2)
            // atur textColor
            btnPopular.setTextColor(ContextCompat.getColor(this, R.color.dark_blue))
            btnNewest.setTextColor(ContextCompat.getColor(this, R.color.white))
            // atur RecycleView
            binding.frameLayoutCard.visibility = View.GONE
            binding.frameLayoutCard2.visibility = View.VISIBLE
        }

        // variable dengan lambda
        val callback = object : OnBackPressedCallback(true) {
            // function menangani tombol kembali
            override fun handleOnBackPressed() {
                if (!doubleBackToExitPressedOnce) {
                    doubleBackToExitPressedOnce = true
                    // jika hanya menekan sekali akan muncul toast
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                    Handler(Looper.getMainLooper()).postDelayed({
                        doubleBackToExitPressedOnce = false
                    }, 2000)
                } else {
                    // Menutup semua aktivitas dan memulai kembali
                    finishAffinity()
                }
                when(numberClicked){
                    1 -> message = "Tekan sekali lagi untuk keluar"
                    2 -> message = "😁😁😁Bre bre kalau mau keluar tekan lagi donnnggg!"
                    3 -> message = "😒Et bocah yaaa, jangan di mainin napa tombolnya!!!😒"
                }
                numberClicked++
                if(numberClicked == 4) {
                    numberClicked = 1
                }
            }
        }

        // menjalankan aksi function callback
        onBackPressedDispatcher.addCallback(this, callback)

        // mendeteksi sentuhan di luar keyboard akan menutup keyboard
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
    }

    // LayoutManager
    private fun init() {
        // RecycleView 1
        binding.card1.layoutManager = LinearLayoutManager(this@MainActivity)
        // RecycleView 2
        binding.card2.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    // set array
    private fun setRvAdapter() {
        // RecycleView 1
        val dataList: MutableList<ItemModel> = mutableListOf()
        nameHead().forEachIndexed{index, name ->
            dataList.add(ItemModel(ImageItem().get(index),name,descName()[index],dateName()[index],btnName()[index], btnName2()[index]))
        }

        // RecycleView 2
        val dataList2: MutableList<ItemModel2> = mutableListOf()
        nameHead2().forEachIndexed{index, name ->
            dataList2.add(ItemModel2(ImageItem2().get(index),name,descName2()[index],dateName2()[index],btnName3()[index], btnName4()[index]))
        }
        
          // debugging mendapatkan data dataList
//        Log.d("ISI DATANYA", dataList.toString())
        
        // binding RecycleView 1
        adapterMain = RvItem(dataList)
        binding.card1.adapter = adapterMain
        // binding RecycleView 2
        adapterMain2 = RvItem2(dataList2)
        binding.card2.adapter = adapterMain2

        // debuggin mendapatkan index jika card di click
//        adapterMain.setOnItemClickListener(object : RvItem.OnItemClickListener {
//            override fun onItemClick(position: Int) {
//                Log.d("ItemClicked", "Clicked item at index: $position")
//            }
//        })
    }

    // RecycleView 1
    // array
    private fun nameHead(): Array<String> = resources.getStringArray(R.array.data_name)
    private fun descName(): Array<String> = resources.getStringArray(R.array.data_description)
    private fun dateName(): Array<String> = resources.getStringArray(R.array.data_date)
    private fun btnName(): Array<String> = resources.getStringArray(R.array.data_btn)
    private fun btnName2(): Array<String> = resources.getStringArray(R.array.data_btn2)
    private fun ImageItem():List<Int> = listOf(
        R.drawable.photo1,
        R.drawable.photo2,
        R.drawable.photo3,
        R.drawable.photo4
    )

    // RecycleView 2
    // array
    private fun nameHead2(): Array<String> = resources.getStringArray(R.array.data_name2)
    private fun descName2(): Array<String> = resources.getStringArray(R.array.data_description2)
    private fun dateName2(): Array<String> = resources.getStringArray(R.array.data_date2)
    private fun btnName3(): Array<String> = resources.getStringArray(R.array.data_btn3)
    private fun btnName4(): Array<String> = resources.getStringArray(R.array.data_btn4)
    private fun ImageItem2():List<Int> = listOf(
        R.drawable.photo5,
        R.drawable.photo6,
    )
}