package com.example.zenv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zenv.adapters.RvItem
import com.example.zenv.databinding.ActivityMainBinding
import com.example.zenv.model.ItemModel

class MainActivity : AppCompatActivity() {
    // declaration binding
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterMain: RvItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setRvAdapter()
    }

    // LayoutManager
    private fun init() {
        binding.card1.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    // set array
    private fun setRvAdapter() {
        val dataList: MutableList<ItemModel> = mutableListOf()

        nameHead().forEachIndexed{index, name ->
            dataList.add(ItemModel(ImageItem().get(index),name,descName()[index],dateName()[index],btnName()[index], btnName2()[index]))
        }

        Log.d("ISI DATANYA", dataList.toString())
        adapterMain = RvItem(dataList)
        binding.card1.adapter =adapterMain

        // debuggin mendapatkan index jika card di click
//        adapterMain.setOnItemClickListener(object : RvItem.OnItemClickListener {
//            override fun onItemClick(position: Int) {
//                Log.d("ItemClicked", "Clicked item at index: $position")
//            }
//        })
    }

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
}