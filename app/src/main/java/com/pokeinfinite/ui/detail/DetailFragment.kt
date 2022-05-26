package com.pokeinfinite.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.pokeinfinite.R
import com.pokeinfinite.data.ApiResource
import com.pokeinfinite.data.model.PokemonSpeciesResponse
import com.pokeinfinite.data.model.SinglePokemonResponse
import com.pokeinfinite.data.model.TypesItem
import com.pokeinfinite.databinding.FragmentDetailBinding
import com.pokeinfinite.ui.adapter.ItemPokeStatsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()
    private val statsAdapter by lazy { ItemPokeStatsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)
        binding.lifecycleOwner = this
        binding.pokeDetail = viewModel
        initViewModel()
    }

    private fun initViewModel() {
        val pokemonName: String = args.pokemonName

        viewModel.getPokemonDetail(pokemonName)
        viewModel.pokemonDetailResponse.observe(viewLifecycleOwner) { pokemonDetail ->
            when (pokemonDetail) {
                is ApiResource.Loading -> {}
                is ApiResource.Success -> {
                    pokemonDetail.data?.let { initPokeDetail(it) }
                }
                is ApiResource.Error -> {}
            }
        }

        viewModel.getPokemonSpecies(pokemonName)
        viewModel.pokemonSpeciesResponse.observe(viewLifecycleOwner) { pokemonDetail ->
            when (pokemonDetail) {
                is ApiResource.Loading -> {}
                is ApiResource.Success -> {
                    pokemonDetail.data?.let { initPokeSpecies(it) }
                }
                is ApiResource.Error -> {}
            }
        }
    }

    private fun initPokeDetail(data: SinglePokemonResponse) {
        binding.apply {
            val pokemonImage = data.sprites?.other?.officialArtwork?.frontDefault

//            loadImage(ivDetailPokemon, pokemonImage)
//            tvDetailPokemonNumber.text = getString(R.string.pokemon_number_format, data.id)
            tvDetailPokemonName.text = data.name.replaceFirstChar { it.uppercase() }
            tvDetailBaseXp.text = data.baseExperience.toString()
            tvDetailHeight.text = getString(R.string.pokemon_format_height, (data.height.times(10)))
            tvDetailWeight.text = getString(R.string.pokemon_format_weight, (data.weight.div(10.0)))
            tvDetailPokemonTypeOne.text = data.types[0].type.name
            setPokemonTypes(data.types)
        }
    }

    private fun initPokeSpecies(data: PokemonSpeciesResponse) {
        binding.apply {
            var flavorText = data.flavorTextEntries[1].flavorText
            flavorText = flavorText.replace("POKéMON", "Pokémon")
            flavorText = flavorText.replace("\n", " ")

            tvDetailPokemonDesc.text = flavorText
            tvDetailCatchRate.text = data.captureRate.toString()
        }
    }

    private fun setPokemonTypes(types: List<TypesItem>) {
        with(binding) {
            if (types.size > 1) {
                tvDetailPokemonTypeTwo.text = types[1].type.name
                tvDetailPokemonTypeTwo.visibility = View.VISIBLE
            } else {
                tvDetailPokemonTypeTwo.visibility = View.GONE
            }
        }
    }

    private fun loadImage(image: ImageView, url: String?) {
        image.load(url) {
            crossfade(200)
            error(R.drawable.ic_launcher_foreground)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}