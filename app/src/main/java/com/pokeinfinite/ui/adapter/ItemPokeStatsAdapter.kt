package com.pokeinfinite.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pokeinfinite.data.model.SinglePokemonResponse
import com.pokeinfinite.data.model.StatsItem
import com.pokeinfinite.databinding.ItemPokemonStatsBinding
import com.pokeinfinite.utils.DiffUtils

class ItemPokeStatsAdapter : RecyclerView.Adapter<ItemPokeStatsAdapter.StatViewHolder>() {

    private var statsResult = emptyList<StatsItem>()

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

    fun statsDiffUtil(newData: SinglePokemonResponse) {
        val statsDiffUtil = DiffUtils(statsResult, newData.stats)
        val diffUtilResult = DiffUtil.calculateDiff(statsDiffUtil)
        statsResult = newData.stats
        diffUtilResult.dispatchUpdatesTo(this)
    }

}