package com.pokeinfinite.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.pokeinfinite.R
import com.pokeinfinite.databinding.FragmentHomeBinding
import com.pokeinfinite.ui.adapter.ItemLoadStateAdapter
import com.pokeinfinite.ui.adapter.ItemPokemonPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()
    private val adapter by lazy { ItemPokemonPagingAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        initRecyclerView()
        initViewModel()

    }

    private fun initRecyclerView() {
        binding.apply {
            rvPokemonList.setHasFixedSize(true)
            rvPokemonList.adapter = adapter.withLoadStateHeaderAndFooter(
                header = ItemLoadStateAdapter { adapter.retry() },
                footer = ItemLoadStateAdapter { adapter.retry() }
            )
            btnErrorLoad.setOnClickListener { adapter.retry() }

            adapter.addLoadStateListener { loadState ->
                pbPokemonList.isVisible = loadState.source.refresh is LoadState.Loading
                tvErrorLoad.isVisible = loadState.source.refresh is LoadState.Error
                btnErrorLoad.isVisible = loadState.source.refresh is LoadState.Error
            }

        }

    }

    private fun initViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pokemonPaging.collect { adapter.submitData(it) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}