package com.pokeinfinite.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pokeinfinite.data.ApiResponse
import com.pokeinfinite.data.model.PokemonResult
import com.pokeinfinite.data.model.PokemonSpeciesResponse
import com.pokeinfinite.data.model.SinglePokemonResponse
import com.pokeinfinite.data.network.ApiService
import com.pokeinfinite.utils.Constants.LIMIT
import com.pokeinfinite.utils.Constants.OFFSET
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {

    override fun getPokemonPagingSource(): PagingSource<Int, PokemonResult> {
        return object : PagingSource<Int, PokemonResult>() {

            override fun getRefreshKey(state: PagingState<Int, PokemonResult>): Int? {
                return state.anchorPosition
            }

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonResult> {
                return try {
                    val pokemonOffset = params.key ?: OFFSET

                    val response = apiService.getPokemonResponse(pokemonOffset, LIMIT)
                    val pokemon = response.results

                    val prevKey = if (pokemonOffset == OFFSET) null else pokemonOffset - LIMIT
                    val nextKey = if (pokemon.size < 20 || pokemon.isEmpty()) null else pokemonOffset + LIMIT

                    LoadResult.Page(
                        data = pokemon,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )

                } catch (exception: IOException) {
                    LoadResult.Error(exception)
                } catch (exception: HttpException) {
                    LoadResult.Error(exception)
                }
            }

        }
    }

    override suspend fun getPokemonDetail(queryName: String): Flow<ApiResponse<SinglePokemonResponse>> {
        return flow {
            try {
                val response = apiService.getSinglePokemonResponse(queryName)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getPokemonSpecies(queryName: String): Flow<ApiResponse<PokemonSpeciesResponse>> {
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