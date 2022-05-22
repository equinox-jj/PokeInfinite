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

    private val mHomeViewModel by viewModels<HomeViewModel>()
    private val mPokemonPagingAdapter by lazy { ItemPokemonPagingAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        initRecyclerView()
        initViewModel()

    }

    private fun initRecyclerView() {
        binding.apply {
            rvPokemonList.setHasFixedSize(true)
            rvPokemonList.adapter = mPokemonPagingAdapter.withLoadStateHeaderAndFooter(
                header = ItemLoadStateAdapter { mPokemonPagingAdapter.retry() },
                footer = ItemLoadStateAdapter { mPokemonPagingAdapter.retry() }
            )
            btnErrorLoad.setOnClickListener { mPokemonPagingAdapter.retry() }
        }
        mPokemonPagingAdapter.addLoadStateListener { loadState ->
            binding.apply {
                tvErrorLoad.isVisible = loadState.source.refresh is LoadState.Error
                btnErrorLoad.isVisible = loadState.source.refresh is LoadState.Error
//                tvErrorLoad.isVisible = loadState.source.refresh is LoadState.Loading

//                if (loadState.source.refresh is LoadState.NotLoading &&
//                    loadState.append.endOfPaginationReached &&
//                    mPokemonPagingAdapter.itemCount < 1
//                ) {
//                    rvPokemonList.isVisible = false
//                } else {
//                textViewEmpty.isVisible = false
//                }
            }

        }

    }

    private fun initViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            mHomeViewModel.pokemonPaging.collect { mPokemonPagingAdapter.submitData(it) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}