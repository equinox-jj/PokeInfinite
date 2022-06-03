package com.pokeinfinite.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pokeinfinite.data.ApiResource
import com.pokeinfinite.data.ApiResponse
import com.pokeinfinite.data.model.PokemonResult
import com.pokeinfinite.data.model.PokemonSpeciesResponse
import com.pokeinfinite.data.model.SinglePokemonResponse
import com.pokeinfinite.data.source.remote.PokemonPagingSource
import com.pokeinfinite.data.source.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val pokemonPagingSource: PokemonPagingSource
) : PokemonRepository {

    override fun getPokemonPaging(): Flow<PagingData<PokemonResult>> = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = 20),
        pagingSourceFactory = { pokemonPagingSource }
    ).flow

    override fun getPokemonDetail(queryName: String): Flow<ApiResource<SinglePokemonResponse>> =
        flow {
            emit(ApiResource.Loading())
            when (val response = remoteDataSource.getPokemonDetail(queryName).first()) {
                is ApiResponse.Success -> {
                    val data = response.data
                    emit(ApiResource.Success(data))
                }
                is ApiResponse.Error -> {
                    emit(ApiResource.Error(response.errorMessage))
                }
                is ApiResponse.Empty -> {}
            }
        }

    override fun getPokemonSpecies(queryName: String): Flow<ApiResource<PokemonSpeciesResponse>> =
        flow {
            emit(ApiResource.Loading())
            when (val response = remoteDataSource.getPokemonSpecies(queryName).first()) {
                is ApiResponse.Success -> {
                    val data = response.data
                    emit(ApiResource.Success(data))
                }
                is ApiResponse.Error -> {
                    emit(ApiResource.Error(response.errorMessage))
                }
                is ApiResponse.Empty -> {}
            }
        }

}