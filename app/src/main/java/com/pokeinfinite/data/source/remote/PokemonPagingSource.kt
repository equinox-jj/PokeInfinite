package com.pokeinfinite.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pokeinfinite.data.model.PokemonResult
import com.pokeinfinite.data.network.ApiService
import com.pokeinfinite.utils.Constants
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PokemonPagingSource @Inject constructor(
    private val apiService: ApiService
) : PagingSource<Int, PokemonResult>() {

    override fun getRefreshKey(state: PagingState<Int, PokemonResult>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonResult> {
        return try {
            val pokemonOffset = params.key ?: Constants.OFFSET

            val response = apiService.getPokemonResponse(pokemonOffset, Constants.LIMIT)
            val pokemon = response.results

            val prevKey =
                if (pokemonOffset == Constants.OFFSET) null else pokemonOffset - Constants.LIMIT
            val nextKey =
                if (pokemon.size < 20 || pokemon.isEmpty()) null else pokemonOffset + Constants.LIMIT

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