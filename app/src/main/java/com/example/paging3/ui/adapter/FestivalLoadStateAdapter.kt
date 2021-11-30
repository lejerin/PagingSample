package com.example.paging3.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3.R
import com.example.paging3.databinding.LoadStateViewBinding

class FestivalLoadStateAdapter (private val retry: () -> Unit) : LoadStateAdapter<FestivalLoadStateAdapter.LoadStateViewHolder>(){
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {

        holder.binding.loadStateRetry.isVisible = loadState !is LoadState.Loading
        holder.binding.loadStateErrorMessage.isVisible = loadState !is LoadState.Loading
        holder.binding.loadStateProgress.isVisible = loadState is LoadState.Loading

        if (loadState is LoadState.Error){
            holder.binding.loadStateErrorMessage.text = loadState.error.localizedMessage
        }

        holder.binding.loadStateRetry.setOnClickListener {
            retry.invoke()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.load_state_view,parent,false)
        return LoadStateViewHolder(view)
    }

    inner class LoadStateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val binding = LoadStateViewBinding.bind(itemView)
        }
}