package com.pokeinfinite.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pokeinfinite.data.model.SinglePokemonResponse
import com.pokeinfinite.data.model.TypesItem
import com.pokeinfinite.databinding.ItemPokemonTypesBinding
import com.pokeinfinite.utils.DiffUtils

class ItemPokeTypesAdapter : RecyclerView.Adapter<ItemPokeTypesAdapter.TypesViewHolder>() {

    private var typesResult = ArrayList<TypesItem>()

    inner class TypesViewHolder(private val binding: ItemPokemonTypesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(typesPoke: TypesItem) {
            binding.pokeTypes = typesPoke
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypesViewHolder {
        val binding = ItemPokemonTypesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TypesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TypesViewHolder, position: Int) {
        val currentTypes = typesResult[position]
        holder.bind(currentTypes)
    }

    override fun getItemCount(): Int = typesResult.size

    fun typesDiffUtil(newData: SinglePokemonResponse) {
        val typesDiffUtil = DiffUtils(typesResult, newData.types)
        val diffUtil = DiffUtil.calculateDiff(typesDiffUtil)
        typesResult = newData.types as ArrayList<TypesItem>
        diffUtil.dispatchUpdatesTo(this)
    }
}