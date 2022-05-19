package com.pokeinfinite.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pokeinfinite.data.model.PokemonResponse
import com.pokeinfinite.data.network.ApiService
import com.pokeinfinite.utils.Constants.OFFSET
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PokemonPagingSource @Inject constructor(
    private val apiService: ApiService
) : PagingSource<Int, PokemonResponse.PokemonResult>() {

    override fun getRefreshKey(state: PagingState<Int, PokemonResponse.PokemonResult>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonResponse.PokemonResult> {
        val pokemonOffset = params.key ?: OFFSET

        return try {
            val response = apiService.getPokemonResponse(pokemonOffset, params.loadSize)
            val pokemon = response.results

            LoadResult.Page(
                data = pokemon,
                prevKey = if (pokemonOffset == OFFSET) null else pokemonOffset - 1,
                nextKey = if (pokemon.isEmpty()) null else pokemonOffset + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}