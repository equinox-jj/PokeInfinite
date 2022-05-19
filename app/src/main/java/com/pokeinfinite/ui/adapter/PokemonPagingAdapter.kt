package com.pokeinfinite.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.pokeinfinite.data.model.PokemonResponse
import com.pokeinfinite.databinding.ItemPokemonListBinding

class PokemonPagingAdapter : PagingDataAdapter<PokemonResponse.PokemonResult, PokemonPagingAdapter.PokemonViewHolder>(POKEMON_COMPARATOR) {

    inner class PokemonViewHolder(private val binding: ItemPokemonListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemonResponse: PokemonResponse.PokemonResult) {
            val pokemonImage = pokemonResponse.getPokemonImage()
            binding.tvPokemonList.text = pokemonResponse.name
            binding.ivPokemonList.load(pokemonImage) {
                crossfade(200)
            }
        }
    }

    override fun onBindViewHolder(holder: PokemonPagingAdapter.PokemonViewHolder, position: Int) {
        val currentPokemon = getItem(position)
        if (currentPokemon != null) {
            holder.bind(currentPokemon)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonPagingAdapter.PokemonViewHolder {
        val binding = ItemPokemonListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    companion object {
        private val POKEMON_COMPARATOR =
            object : DiffUtil.ItemCallback<PokemonResponse.PokemonResult>() {
                override fun areItemsTheSame(
                    oldItem: PokemonResponse.PokemonResult,
                    newItem: PokemonResponse.PokemonResult
                ) = oldItem.name == newItem.name

                override fun areContentsTheSame(
                    oldItem: PokemonResponse.PokemonResult,
                    newItem: PokemonResponse.PokemonResult
                ) = oldItem == newItem
            }
    }
}