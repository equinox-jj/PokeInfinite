package com.pokeinfinite.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pokeinfinite.data.model.PokemonResult
import com.pokeinfinite.data.network.ApiService
import com.pokeinfinite.utils.Constants.LIMIT
import com.pokeinfinite.utils.Constants.OFFSET
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

                    LoadResult.Page(
                        data = pokemon,
                        prevKey = if (pokemonOffset == OFFSET) null else pokemonOffset - LIMIT,
                        nextKey = if (pokemon.size < 20 || pokemon.isEmpty()) null else pokemonOffset + LIMIT
                    )

                } catch (exception: IOException) {
                    LoadResult.Error(exception)
                } catch (exception: HttpException) {
                    LoadResult.Error(exception)
                }
            }

        }
    }
}