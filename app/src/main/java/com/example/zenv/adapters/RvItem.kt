package com.example.zenv.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zenv.DetailActivity
import com.example.zenv.databinding.CardItemBinding
import com.example.zenv.model.ItemModel

class RvItem(private val list: List<ItemModel>): RecyclerView.Adapter<RvItem.ViewHolder>(){
    // declaration variable untuk menagani click menggunakan nullable safety
    private var listener: OnItemClickListener? = null
    // function untuk menset onclick
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    // untuk mendapatkan data pada card_item.xml
    class ViewHolder(val binding: CardItemBinding): RecyclerView.ViewHolder(binding.root)
    // untuk membuat data
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    // untuk mendapatkan total keseluruhan ukuran data
    override fun getItemCount(): Int = list.size
    // untuk mencocokan data yang di terima
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // untuk mendapatkan data id kemudian akan di isikan value array
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

        // untuk menangani click pada setiap card
        holder.itemView.setOnClickListener {
            listener?.onItemClick(position)

            // untuk mendapatkan index tertentu dan akan melakukan aksi intent
            if (position == 0) {
                val intent = Intent(holder.itemView.context, DetailActivity::class.java)
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    // untuk mendapatkan index click
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}