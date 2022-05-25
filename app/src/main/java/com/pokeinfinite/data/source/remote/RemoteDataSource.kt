package com.pokeinfinite.data.source.remote

import androidx.paging.PagingSource
import com.pokeinfinite.data.ApiResponse
import com.pokeinfinite.data.model.PokemonResult
import com.pokeinfinite.data.model.PokemonSpeciesResponse
import com.pokeinfinite.data.model.SinglePokemonResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getPokemonPagingSource(): PagingSource<Int, PokemonResult>
    fun getPokemonDetail(queryName: String): Flow<ApiResponse<SinglePokemonResponse>>
    fun getPokemonSpecies(queryName: String): Flow<ApiResponse<PokemonSpeciesResponse>>

}