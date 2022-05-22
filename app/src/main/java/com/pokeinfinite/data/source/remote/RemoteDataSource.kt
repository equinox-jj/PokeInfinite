package com.pokeinfinite.data.source.remote

import androidx.paging.PagingSource
import com.pokeinfinite.data.model.PokemonResponse
import com.pokeinfinite.data.model.PokemonResult

interface RemoteDataSource {

    fun getPokemonPagingSource(): PagingSource<Int, PokemonResult>

}