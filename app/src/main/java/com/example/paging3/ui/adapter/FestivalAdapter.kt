package com.example.paging3.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3.data.FestivalItem
import com.example.paging3.databinding.ItemFestivalBinding


class FestivalAdapter(val action: (ImageView, FestivalItem) -> Unit):
    PagingDataAdapter<FestivalItem, FestivalAdapter.FestivalViewHolder>(FestivalComparator()){

    override fun onBindViewHolder(holder: FestivalViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FestivalViewHolder {
        return FestivalViewHolder(
            ItemFestivalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class FestivalViewHolder(private val binding: ItemFestivalBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FestivalItem){
            binding.apply {
                root.setOnClickListener {
                    action(binding.festivalImage, item)
                }
                festivalItem = item
                executePendingBindings()
            }
        }
    }
}

private class FestivalComparator : DiffUtil.ItemCallback<FestivalItem>() {
    override fun areItemsTheSame(oldItem: FestivalItem, newItem: FestivalItem): Boolean {
        return oldItem.contentid == newItem.contentid
    }

    override fun areContentsTheSame(oldItem: FestivalItem, newItem: FestivalItem): Boolean {
        return oldItem.contentid == newItem.contentid
    }
}