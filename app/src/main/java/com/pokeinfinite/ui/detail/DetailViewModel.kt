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

    private val _pokemonSpeciesResponse = MutableLiveData<ApiResource<PokemonSpeciesResponse>>()
    val pokemonSpeciesResponse: LiveData<ApiResource<PokemonSpeciesResponse>>
        get() = _pokemonSpeciesResponse

    fun getPokemonDetail(queryName: String) {
        viewModelScope.launch {
            repository.getPokemonDetail(queryName)
                .onStart {
                    _pokemonDetailResponse.postValue(ApiResource.Loading())
                }
                .catch { error ->
                    error.message?.let { message ->
                        _pokemonDetailResponse.postValue(ApiResource.Error(message))
                    }
                }
                .collect { pokeDetail ->
                    pokeDetail.data?.let {
                        _pokemonDetailResponse.postValue(ApiResource.Success(it))
                    }
                }
        }
    }

    fun getPokemonSpecies(queryName: String) {
        viewModelScope.launch {
            repository.getPokemonSpecies(queryName)
                .onStart {
                    _pokemonSpeciesResponse.postValue(ApiResource.Loading())
                }
                .catch { error ->
                    error.message?.let { message ->
                        _pokemonSpeciesResponse.postValue(ApiResource.Error(message))
                    }

                }
                .collect { pokeSpecies ->
                    pokeSpecies.data?.let {
                        _pokemonSpeciesResponse.postValue(ApiResource.Success(it))
                    }
                }
        }
    }

}