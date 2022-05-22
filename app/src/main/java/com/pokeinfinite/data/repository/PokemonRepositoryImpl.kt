package com.pokeinfinite.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pokeinfinite.data.model.PokemonResult
import com.pokeinfinite.data.source.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : PokemonRepository {

    override fun getPokemonPaging(): Flow<PagingData<PokemonResult>> = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = 20),
        pagingSourceFactory = { remoteDataSource.getPokemonPagingSource() }
    ).flow

}