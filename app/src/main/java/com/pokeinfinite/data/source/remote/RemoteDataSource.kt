package com.pokeinfinite.data.source.remote

import androidx.paging.PagingSource
import com.pokeinfinite.data.model.PokemonResponse

interface RemoteDataSource {

    fun getPokemonPagingSource(): PagingSource<Int, PokemonResponse.PokemonResult>

}