package com.phono.captionstar.presentation.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.phono.captionstar.R
import com.phono.captionstar.data.remote.dto.DetailsDto
import com.phono.captionstar.data.remote.dto.FeedDto
import com.phono.captionstar.databinding.LayoutDetailsCardBinding
import com.phono.captionstar.databinding.LayoutFeedDetailsBinding

class FeedDetailsAdapter(
    private val feedDto: FeedDto
) : RecyclerView.Adapter<FeedDetailsAdapter.FeedDetailsAdapterViewHolder>() {

    private lateinit var binding: LayoutFeedDetailsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedDetailsAdapterViewHolder {
        binding =
            LayoutFeedDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedDetailsAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedDetailsAdapterViewHolder, position: Int) {
        holder.apply {
            binding.feedContent.text = feedDto.content
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    inner class FeedDetailsAdapterViewHolder(binding: LayoutFeedDetailsBinding) :
        RecyclerView.ViewHolder(binding.root)
}