package com.pokeinfinite.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pokeinfinite.data.model.PokemonResponse
import com.pokeinfinite.data.network.ApiService
import com.pokeinfinite.data.source.remote.PokemonPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getPokemonPaging(): Flow<PagingData<PokemonResponse.PokemonResult>> = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = 20),
        pagingSourceFactory = { PokemonPagingSource(apiService) }
    ).flow

    /*suspend fun getPokemonPaging(): Flow<PagingData<PokemonResponse.PokemonResult>> = Pager(
    config = PagingConfig(
        pageSize = 20,
        enablePlaceholders = false
    ),
    pagingSourceFactory = { PokemonPagingSource(apiService) }
).flow*/

    /*fun getSearchResults(query: String) = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { UnsplashPagingSource(unsplashApi, query) }
    ).liveData*/
}