package com.example.zenv.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zenv.databinding.CardItemBinding
import com.example.zenv.model.ItemModel

class RvItem(private val list: List<ItemModel>): RecyclerView.Adapter<RvItem.ViewHolder>(){

    class ViewHolder(val binding: CardItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(list[position]) {
                binding.ivPhoto.setImageResource(this.image)
                binding.tvPhoto.text = this.name
                binding.tvDesc.text = this.desc
                binding.tvDate.text = this.date
                binding.btnSatu.text = this.btn
                binding.btnDua.text = this.btn2
            }
        }
    }
}