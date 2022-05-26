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
import com.phono.captionstar.databinding.LayoutDetailsCardBinding

class DetailsAdapter(
    private val context: Context,
    private val items: List<DetailsDto>
) : RecyclerView.Adapter<DetailsAdapter.DetailsAdapterViewHolder>() {

    private lateinit var binding: LayoutDetailsCardBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsAdapterViewHolder {
        binding =
            LayoutDetailsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailsAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsAdapterViewHolder, position: Int) {
        val item = items[position]
        holder.apply {
            binding.caption.text = item.caption
        }
        binding.copy.setOnClickListener {
            val clipboardManager =
                holder.itemView.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("text", item.caption)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_LONG)
                .show()
        }

        binding.share.setOnClickListener {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, item.caption)
            context.startActivity(
                Intent.createChooser(
                    shareIntent,
                    context.getString(
                        R.string.share_via
                    )
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class DetailsAdapterViewHolder(binding: LayoutDetailsCardBinding) :
        RecyclerView.ViewHolder(binding.root)
}