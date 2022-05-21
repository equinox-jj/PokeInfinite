package com.pokeinfinite.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pokeinfinite.databinding.ItemLoadStateAdapterBinding

class ItemLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<ItemLoadStateAdapter.LoadStateViewHolder>() {

    inner class LoadStateViewHolder(private val binding: ItemLoadStateAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnLoadAdapter.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                pbLoadAdapter.isVisible = loadState is LoadState.Loading
                btnLoadAdapter.isVisible = loadState !is LoadState.Loading
                tvLoadAdapter.isVisible = loadState !is LoadState.Loading
            }
        }
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = ItemLoadStateAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding)
    }

}