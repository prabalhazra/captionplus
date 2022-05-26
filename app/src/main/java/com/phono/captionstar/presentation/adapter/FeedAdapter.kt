package com.phono.captionstar.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.phono.captionstar.data.remote.dto.FeedDto
import com.phono.captionstar.databinding.LayoutFeedItemsBinding
import com.phono.captionstar.presentation.feed.FeedFragmentDirections

class FeedAdapter(
    private val context: Context,
    private val feeds: List<FeedDto>
) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    private lateinit var binding: LayoutFeedItemsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        binding = LayoutFeedItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val feedItem = feeds[position]
        holder.apply {
            binding.feedTitle.text = feedItem.title
            binding.feedDate.text = feedItem.date
            Glide.with(context).load(feedItem.url).into(binding.feedImage)
        }

        binding.feedCardView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToFeedDetailsFragment(feedItem)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return feeds.size
    }

    inner class FeedViewHolder(binding: LayoutFeedItemsBinding) :
        RecyclerView.ViewHolder(binding.root)
}