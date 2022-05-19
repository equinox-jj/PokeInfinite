package com.pokeinfinite.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pokeinfinite.R
import com.pokeinfinite.databinding.FragmentHomeBinding
import com.pokeinfinite.ui.adapter.PokemonPagingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val mHomeViewModel by viewModels<HomeViewModel>()
    private lateinit var mPokemonPagingAdapter: PokemonPagingAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        initRecyclerView()
        initViewModel()

    }

    private fun initRecyclerView() {
        binding.rvPokemonList.apply {
            mPokemonPagingAdapter = PokemonPagingAdapter()
            adapter = mPokemonPagingAdapter
        }
    }

    private fun initViewModel() {
        mHomeViewModel.getPokemonPagingSource()
        mHomeViewModel.pokemonPagingData.observe(viewLifecycleOwner) {
            mPokemonPagingAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            binding.rvPokemonList.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}