package com.pokeinfinite.data.source.remote

import com.pokeinfinite.data.ApiResponse
import com.pokeinfinite.data.model.PokemonSpeciesResponse
import com.pokeinfinite.data.model.SinglePokemonResponse
import com.pokeinfinite.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    fun getPokemonDetail(queryName: String): Flow<ApiResponse<SinglePokemonResponse>> {
        return flow {
            try {
                val response = apiService.getSinglePokemonResponse(queryName)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getPokemonSpecies(queryName: String): Flow<ApiResponse<PokemonSpeciesResponse>> {
        return flow {
            try {
                val response = apiService.getPokemonSpeciesResponse(queryName)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}