package com.phono.captionstar.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.phono.captionstar.data.remote.dto.HomeDto
import com.phono.captionstar.databinding.LayoutHomeBinding
import dagger.hilt.android.qualifiers.ApplicationContext

class HomeAdapter (
    private val context: Context,
    private val item: List<HomeDto>
): RecyclerView.Adapter<HomeAdapter.HomeAdapterViewHolder>() {


    inner class HomeAdapterViewHolder(binding: LayoutHomeBinding) : RecyclerView.ViewHolder(binding.root)
    private lateinit var binding: LayoutHomeBinding
    private lateinit var adapter: HomeItemAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapterViewHolder {
        binding = LayoutHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeAdapterViewHolder, position: Int) {
        val itemPosition = item[position]
        adapter = HomeItemAdapter(itemPosition.items!!, context)
        holder.apply {
            binding.captionName.text = itemPosition.name
            binding.homeItemsRecyclerView.adapter = adapter
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }
}