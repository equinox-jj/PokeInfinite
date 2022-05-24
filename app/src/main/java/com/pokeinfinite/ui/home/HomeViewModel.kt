package com.pokeinfinite.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.pokeinfinite.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: PokemonRepository
) : ViewModel() {

    val pokemonPaging = repository.getPokemonPaging().cachedIn(viewModelScope)

}