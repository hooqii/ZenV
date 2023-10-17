package com.example.zenv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setRvAdapter()

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