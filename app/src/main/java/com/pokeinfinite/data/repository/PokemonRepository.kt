package com.pokeinfinite.data.repository

import androidx.paging.PagingData
import com.pokeinfinite.data.ApiResource
import com.pokeinfinite.data.model.PokemonResult
import com.pokeinfinite.data.model.PokemonSpeciesResponse
import com.pokeinfinite.data.model.SinglePokemonResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemonPaging(): Flow<PagingData<PokemonResult>>
    suspend fun getPokemonDetail(queryName: String): Flow<ApiResource<SinglePokemonResponse>>
    suspend fun getPokemonSpecies(queryName: String): Flow<ApiResource<PokemonSpeciesResponse>>

}