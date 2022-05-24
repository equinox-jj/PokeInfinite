package com.pokeinfinite.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.pokeinfinite.R
import com.pokeinfinite.data.ApiResource
import com.pokeinfinite.data.model.SinglePokemonResponse
import com.pokeinfinite.databinding.FragmentDetailBinding
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
        viewModel.getPokemonPagingSource(pokemonName)
        viewModel.pokemonDetailResponse.observe(viewLifecycleOwner) { pokemonDetail ->
            when (pokemonDetail) {
                is ApiResource.Loading -> {}
                is ApiResource.Success -> { pokemonDetail.data?.let { initView(it) } }
                is ApiResource.Error -> {}
            }
        }
    }

    private fun initView(data: SinglePokemonResponse) {
        binding.apply {
            val pokemonImage = data.sprites?.other?.officialArtwork?.frontDefault
            ivDetailPokemon.load(pokemonImage) {
                crossfade(200)
            }
            tvDetailPokemonName.text = data.name
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}