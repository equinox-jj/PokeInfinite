package com.pokeinfinite.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import coil.load
import com.pokeinfinite.R
import com.pokeinfinite.data.ApiResource
import com.pokeinfinite.data.model.PokemonSpeciesResponse
import com.pokeinfinite.data.model.SinglePokemonResponse
import com.pokeinfinite.databinding.FragmentDetailBinding
import com.pokeinfinite.ui.adapter.ItemPokeStatsAdapter
import com.pokeinfinite.ui.adapter.ItemPokeTypesAdapter
import com.skydoves.rainbow.Rainbow
import com.skydoves.rainbow.RainbowOrientation
import com.skydoves.rainbow.color
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()
    private val typesAdapter by lazy { ItemPokeTypesAdapter() }
    private val statsAdapter by lazy { ItemPokeStatsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)
        initRecycler()
        initViewModel()
    }

    private fun initRecycler() {
        binding.rvDetailPokemonStats.apply {
            adapter = statsAdapter
            setHasFixedSize(true)

        }
        binding.rvDetailPokemonTypes.apply {
            adapter = typesAdapter
            setHasFixedSize(true)
        }
    }

    private fun initViewModel() {
        val pokemonName: String = args.pokemonName

        viewModel.getPokemonDetail(pokemonName)
        viewModel.pokemonDetailResponse.observe(viewLifecycleOwner) { pokemonDetail ->
            when (pokemonDetail) {
                is ApiResource.Loading -> {
                    binding.rvDetailPokemonStats.visibility = View.GONE
                    binding.rvDetailPokemonTypes.visibility = View.GONE
                    binding.tvDesc.visibility = View.GONE
                    binding.shapeImageDetail.visibility = View.GONE
                    binding.tvDetailPokemonNumber.visibility = View.GONE
                    binding.cardDesc.visibility = View.GONE
                    binding.cardStats.visibility = View.GONE
                }
                is ApiResource.Success -> {
                    pokemonDetail.data?.let { initPokeDetail(it) }
                    binding.rvDetailPokemonStats.visibility = View.VISIBLE
                    binding.rvDetailPokemonTypes.visibility = View.VISIBLE
                    binding.tvDesc.visibility = View.VISIBLE
                    binding.shapeImageDetail.visibility = View.VISIBLE
                    binding.tvDetailPokemonNumber.visibility = View.VISIBLE
                    binding.cardDesc.visibility = View.VISIBLE
                    binding.cardStats.visibility = View.VISIBLE
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

            setBackgroundPalette(ivDetailPokemon, pokemonImage, shapeImageDetail)
            tvDetailPokemonNumber.text = getString(R.string.pokemon_number_format, data.id)
            tvDetailPokemonName.text = data.name.replaceFirstChar { it.uppercase() }

            tvDetailBaseXp.text = data.baseExperience.toString()
            tvDetailHeight.text = getString(R.string.pokemon_format_height, (data.height.times(10)))
            tvDetailWeight.text = getString(R.string.pokemon_format_weight, (data.weight.div(10.0)))

            statsAdapter.statsDiffUtil(data)
            typesAdapter.typesDiffUtil(data)

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

    private fun setBackgroundPalette(view: ImageView, url: String?, shapeImage: View) {
        view.load(url) {
            allowHardware(false)
            crossfade(200)
            error(R.drawable.ic_launcher_foreground)
            listener(
                onSuccess = { _, result ->
                    Palette.Builder(result.drawable.toBitmap()).generate() { palette ->
                        val light = palette?.lightVibrantSwatch?.rgb
                        val domain = palette?.dominantSwatch?.rgb
                        if (domain != null) {
                            if (light != null) {
                                Rainbow(shapeImage).palette {
                                    +color(domain)
                                    +color(light)
                                }.background(orientation = RainbowOrientation.TOP_BOTTOM)
                            } else {
                                shapeImage.setBackgroundColor(domain)
                            }
                        }
                    }
                }
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}