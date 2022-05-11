package com.phono.captionstar.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.phono.captionstar.data.remote.dto.ItemDto
import com.phono.captionstar.databinding.LayoutHomeItemsBinding

class HomeItemAdapter(
    private val items: List<ItemDto>,
    private val context: Context
) : RecyclerView.Adapter<HomeItemAdapter.HomeItemsViewHolder>() {

    private lateinit var binding: LayoutHomeItemsBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeItemsViewHolder {
        binding = LayoutHomeItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeItemsViewHolder, position: Int) {
        val item = items[position]
        holder.apply {
            binding.name.text = item.name
            Glide.with(context).load(item.url).into(binding.image)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class HomeItemsViewHolder(binding: LayoutHomeItemsBinding) :
        RecyclerView.ViewHolder(binding.root)
}