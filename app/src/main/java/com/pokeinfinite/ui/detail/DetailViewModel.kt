package com.pokeinfinite.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokeinfinite.data.ApiResource
import com.pokeinfinite.data.model.PokemonSpeciesResponse
import com.pokeinfinite.data.model.SinglePokemonResponse
import com.pokeinfinite.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _pokemonDetailResponse = MutableLiveData<ApiResource<SinglePokemonResponse>>()
    val pokemonDetailResponse: LiveData<ApiResource<SinglePokemonResponse>>
        get() = _pokemonDetailResponse

    private val _pokemonDescription = MutableLiveData<ApiResource<PokemonSpeciesResponse>>()
    val pokemonDescription: LiveData<ApiResource<PokemonSpeciesResponse>>
        get() = _pokemonDescription

    fun getPokemonDetail(queryName: String) {
        viewModelScope.launch {
            repository.getPokemonDetail(queryName)
                .onStart {

                }
                .catch {

                }
                .collect {
                    _pokemonDetailResponse.value = it
                }
        }
    }

    fun getPokemonDescription(queryName: String) {
        viewModelScope.launch {
            repository.getPokemonSpecies(queryName)
                .onStart {

                }
                .catch {

                }
                .collect {
                    _pokemonDescription.value = it
                }
        }
    }

}