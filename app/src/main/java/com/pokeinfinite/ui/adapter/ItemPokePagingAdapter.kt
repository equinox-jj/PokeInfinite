package com.pokeinfinite.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pokeinfinite.data.model.PokemonResult
import com.pokeinfinite.databinding.ItemPokemonListBinding

class ItemPokePagingAdapter : PagingDataAdapter<PokemonResult, ItemPokePagingAdapter.PokemonViewHolder>(POKEMON_COMPARATOR) {

    inner class PokemonViewHolder(private val binding: ItemPokemonListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemonResult: PokemonResult) {
            binding.pokemonResults = pokemonResult
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ItemPokePagingAdapter.PokemonViewHolder, position: Int) {
        val currentPokemon = getItem(position)
        if (currentPokemon != null) {
            holder.bind(currentPokemon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPokePagingAdapter.PokemonViewHolder {
        val binding = ItemPokemonListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    companion object {
        private val POKEMON_COMPARATOR = object : DiffUtil.ItemCallback<PokemonResult>() {
            override fun areItemsTheSame(oldItem: PokemonResult, newItem: PokemonResult) =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: PokemonResult, newItem: PokemonResult) =
                oldItem == newItem
        }
    }

}