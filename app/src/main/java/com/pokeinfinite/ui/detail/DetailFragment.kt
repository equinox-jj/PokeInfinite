package com.pokeinfinite.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.pokeinfinite.R
import com.pokeinfinite.data.ApiResource
import com.pokeinfinite.data.model.PokemonSpeciesResponse
import com.pokeinfinite.data.model.SinglePokemonResponse
import com.pokeinfinite.databinding.FragmentDetailBinding
import com.pokeinfinite.utils.formatId
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)
        initViewModel()
    }

    private fun initViewModel() {
        val pokemonName: String = args.pokemonName

        viewModel.getPokemonDetail(pokemonName)
        viewModel.pokemonDetailResponse.observe(viewLifecycleOwner) { pokemonDetail ->
            when (pokemonDetail) {
                is ApiResource.Loading -> {}
                is ApiResource.Success -> {
                    pokemonDetail.data?.let { initViewDetail(it) }
                }
                is ApiResource.Error -> {}
            }
        }

        viewModel.getPokemonDescription(pokemonName)
        viewModel.pokemonDescription.observe(viewLifecycleOwner) { pokemonDetail ->
            when (pokemonDetail) {
                is ApiResource.Loading -> {}
                is ApiResource.Success -> {
                    pokemonDetail.data?.let { initPokeDesc(it) }
                }
                is ApiResource.Error -> {}
            }
        }
    }

    private fun initViewDetail(data: SinglePokemonResponse) {
        binding.apply {
            val pokemonImage = data.sprites?.other?.officialArtwork?.frontDefault
            ivDetailPokemon.load(pokemonImage) {
                crossfade(200)
                error(R.drawable.ic_launcher_foreground)
            }
            tvDetailPokemonNumber.text = formatId(data.id)
            tvDetailPokemonName.text = data.name
        }
    }

    private fun initPokeDesc(data: PokemonSpeciesResponse) {
        binding.apply {
            var flavorText = data.flavorTextEntries[1].flavorText
            flavorText = flavorText.replace("POKéMON", "Pokémon")
            flavorText = flavorText.replace("\n", " ")
            tvDetailPokemonDesc.text = flavorText
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}