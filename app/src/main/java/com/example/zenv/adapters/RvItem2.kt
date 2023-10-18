package com.example.zenv.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zenv.databinding.CardItem2Binding
import com.example.zenv.model.ItemModel2

class RvItem2(private val list2: List<ItemModel2>): RecyclerView.Adapter<RvItem2.ViewHolder>() {
    // untuk mendapatkan data pada card_item.xml
    class ViewHolder(val binding: CardItem2Binding ): RecyclerView.ViewHolder(binding.root)

    // untuk membuat data
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardItem2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // untuk mendapatkan total keseluruhan ukuran data
    override fun getItemCount(): Int = list2.size

    // untuk mencocokan data yang di terima
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // untuk mendapatkan data id kemudian akan di isikan value array
        with(holder){
            with(list2[position]) {
                binding.ivPhoto2.setImageResource(this.image2)
                binding.tvPhoto2.text = this.name2
                binding.tvDesc2.text = this.desc2
                binding.tvDate2.text = this.date2
                binding.btnSatu2.text = this.btn3
                binding.btnDua2.text = this.btn4
            }
        }
    }
}