package com.pokeinfinite.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.pokeinfinite.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: PokemonRepository,
    state: SavedStateHandle
) : ViewModel() {

    val pokemonPaging = repository.getPokemonPaging().cachedIn(viewModelScope)

//    private val _pokemonPagingData = MutableLiveData<PagingData<PokemonResponse.PokemonResult>>()
//    val pokemonPagingData: LiveData<PagingData<PokemonResponse.PokemonResult>> get() = _pokemonPagingData

//    fun getPokemonPagingSource() {
//        viewModelScope.launch {
//            repository.getPokemonPaging()
//                .collectLatest {
//                    _pokemonPagingData.value = it
//                }
//        }
//    }

}