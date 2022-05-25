package com.pokeinfinite.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pokeinfinite.data.model.StatsItem
import com.pokeinfinite.databinding.ItemPokemonStatsBinding

class ItemPokemonStatsAdapter : RecyclerView.Adapter<ItemPokemonStatsAdapter.StatViewHolder>() {

    private val statsResult = emptyList<StatsItem>()

    inner class StatViewHolder(private val binding: ItemPokemonStatsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(statsItem: StatsItem) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatViewHolder {
        val binding = ItemPokemonStatsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatViewHolder, position: Int) {
        val currentStats = statsResult[position]
        holder.bind(currentStats)
    }

    override fun getItemCount(): Int = statsResult.size

}